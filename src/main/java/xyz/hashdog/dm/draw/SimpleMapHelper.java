package xyz.hashdog.dm.draw;

import xyz.hashdog.dm.bean.*;
import xyz.hashdog.dm.bean.base.PicStyle;
import xyz.hashdog.dm.bean.base.Position;
import xyz.hashdog.dm.bean.base.TextStyle;
import xyz.hashdog.dm.draw.convert.CoordinateConvert;
import xyz.hashdog.dm.draw.convert.SameScaleConvert;
import xyz.hashdog.dm.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class SimpleMapHelper extends AbstractMap{
    private MainPic mainPic;
    private Map<SubPicLayer,BufferedImage> subPicsMap;

    private CoordinateConvert convert;
    {
        convert =new SameScaleConvert();
    }

    protected static JMap compile(MainPic mainPic) {
        return new SimpleMapHelper(mainPic);
    }

    private SimpleMapHelper(MainPic mainPic) {
        this.mainPic = mainPic;
    }

    public SimpleMapHelper plot() {
        initMapPic();
        drawSubPics();
        drawMapPic();
        drawText();
        drawPic();
        drawMainPic();
        mergeSubPicsToMain();
        return this;
    }

    /**
     * 画标题文字
     */
    private void drawText() {
        List<Text> texts = mainPic.getTexts();
        for (Text text : texts) {
            BufferedImage bufferedImage = subPicsMap.get(mainPic.getSubPicLayers().get(text.getSubPicIndex()));
            Font font = new Font(text.getFontFamily(),Font.PLAIN,text.getFontSize());
            ImageUtil.DrawString(bufferedImage,text.getTitle(),text.getPosition().getX(),text.getPosition().getY(),font,text.getFontColor(),text.getAlignment());
        }
    }
    /**
     * 画图片标题
     */
    private void drawPic() {
        if(mainPic.getPics()==null)return;
        List<Pic> pics = mainPic.getPics();
        for (Pic pic : pics) {
            BufferedImage bufferedImage = subPicsMap.get(mainPic.getSubPicLayers().get(pic.getSubPicIndex()));
            ImageUtil.drawImage(bufferedImage,pic.getPic(),pic.getPosition().getX(),pic.getPosition().getY());
        }
    }

    /**
     * 连接到主图
     */
    private void mergeSubPicsToMain() {
        List<SubPicLayer> subPicLayers = mainPic.getSubPicLayers();
        //图层排序
        Collections.sort(subPicLayers, new Comparator<SubPicLayer>() {
            @Override
            public int compare(SubPicLayer o1, SubPicLayer o2) {
                return o1.getZorder()-o2.getZorder();
            }
        });
        //叠加
        for (SubPicLayer subPicLayer : subPicLayers) {
            int top = subPicLayer.getTop();
            int left = subPicLayer.getLeft();
            ImageUtil.drawImage(img,subPicsMap.get(subPicLayer),left,top);
        }

    }

    /**
     * 画主图
     */
    private void drawMainPic() {
        img = ImageUtil.getBackgroundImg(mainPic.getWidth(), mainPic.getHight(), mainPic.getAlpha(),mainPic.getFaceColor());
    }

    /**
     * 画子图
     */
    private void drawSubPics() {
        List<SubPicLayer> subPicLayers = mainPic.getSubPicLayers();
        this.subPicsMap=new LinkedHashMap<>();
        for (SubPicLayer subPicLayer : subPicLayers) {
            int width = subPicLayer.getWidth();
            int hight = subPicLayer.getHight();
            String subPicFaceColor = subPicLayer.getFaceColor();
            float subPicAlpha = subPicLayer.getAlpha();
            BufferedImage backgroundImg = ImageUtil.getBackgroundImg(width, hight, subPicAlpha, subPicFaceColor);
            this.subPicsMap.put(subPicLayer,backgroundImg);
        }
    }


    /**
     * 画地图
     */
    private void drawMapPic() {
        List<MapPic> mapPics = mainPic.getMapPics();
        for (MapPic mapPic : mapPics) {
            int subPicIndex = mapPic.getSubPicIndex();
            SubPicLayer subPicLayer = mainPic.getSubPicLayers().get(subPicIndex);
            BufferedImage subImage = subPicsMap.get(subPicLayer);

            convert.init(mapPic, subPicLayer);
            int w = convert.FloorPicWidth();
            int h = convert.FloorPicHieght();
            BufferedImage transparentImg = ImageUtil.getTransparentImg(w, h);
            drawMapBorders(transparentImg,mapPic.getMapBorders(),convert);
            drawMapTexts(transparentImg,mapPic.getMapTexts(),convert);
            drawMapIcons(transparentImg,mapPic.getMapIcons(),convert);
            ImageUtil.drawCenterImg(subImage,transparentImg);
        }
    }

    /**
     * 画图标
     * @param transparentImg
     * @param mapIcons
     * @param convert
     */
    private void drawMapIcons(BufferedImage transparentImg, List<MapIcon> mapIcons, CoordinateConvert convert) {
        for (MapIcon mapIcon : mapIcons) {
            int offsetX = mapIcon.getOffsetX();
            int offsetY = mapIcon.getOffsetY();
            PicStyle picStyle = mapIcon.getPicStyle();
            List<BufferedImage> icons = mapIcon.getIcons();
            List<Double> lons = mapIcon.getLons();
            List<Double> lats = mapIcon.getLats();
            for (int i = 0; i < icons.size(); i++) {
                Position xy = convert.XY(lons.get(i), lats.get(i));
                int x = xy.getX()+offsetX;
                int y = xy.getY()+offsetY;
                ImageUtil.drawCenterImage(transparentImg,icons.get(i),x,y);
            }
        }
    }

    /**
     * 画文本
     * @param transparentImg
     * @param mapTexts
     * @param convert
     */
    private void drawMapTexts(BufferedImage transparentImg, List<MapText> mapTexts, CoordinateConvert convert) {
        for (MapText mapText : mapTexts) {
            int offsetX = mapText.getOffsetX();
            int offsetY = mapText.getOffsetY();
            TextStyle textStyle = mapText.getTextStyle();
            Font font = new Font(textStyle.getFontFamily(),Font.PLAIN,textStyle.getFontSize());
            List<String> text = mapText.getText();
            List<Double> lons = mapText.getLons();
            List<Double> lats = mapText.getLats();
            for (int i = 0; i < lons.size(); i++) {
                Position xy = convert.XY(lons.get(i), lats.get(i));
                int x = xy.getX()+offsetX;
                int y = xy.getY()+offsetY;
                ImageUtil.DrawString(transparentImg,text.get(i),x,y,font,textStyle.getFontColor(),Text.CENTER_CENTER);
            }

        }
    }

    /**
     * 画边界
     * @param transparentImg
     * @param mapBorders
     * @param convert
     */
    private void drawMapBorders(BufferedImage transparentImg, List<MapBorder> mapBorders, CoordinateConvert convert) {
        for (MapBorder mapBorder : mapBorders) {
            String faceColor = mapBorder.getFaceColor();
            String edgeColor = mapBorder.getEdgeColor();
            List<List<Double>> lonsList = mapBorder.getLonsList();
            List<List<Double>> latsList = mapBorder.getLatsList();
            for (int i = 0; i < lonsList.size(); i++) {
                List<Double> lons = lonsList.get(i);
                List<Double> lats = latsList.get(i);
                int [] xPoints=new int[lons.size()];
                int [] yPoints=new int[lons.size()];
                for (int j = 0; j < lons.size(); j++) {
                    Position xy = convert.XY(lons.get(j), lats.get(j));
                    xPoints[j]=xy.getX();
                    yPoints[j]=xy.getY();
                }
                if(xPoints.length>=3){
                    ImageUtil.FillPolygon(transparentImg,xPoints,yPoints,Color.decode(faceColor),mapBorder.getAlpha());
                    ImageUtil.DrawPolygon(transparentImg,xPoints,yPoints,Color.decode(edgeColor),mapBorder.getLineWidth(),mapBorder.getAlpha());
                }
            }
        }
    }


    /**
     * 初始化地图参数
     */
    private void initMapPic() {
        List<MapPic> mapPics = mainPic.getMapPics();
        for (MapPic mapPic : mapPics) {
            if(mapPic.getStartLon()==-1d || mapPic.getEndLon()==-1d || mapPic.getStartLat()==-1d || mapPic.getEndLat()==-1d){
                double maxLon=-9999,maxLat=-9999,minLon=9999,minLat=9999;
                List<MapBorder> mapBorders = mapPic.getMapBorders();
                for (MapBorder mapBorder : mapBorders) {
                    List<List<Double>> lonsList = mapBorder.getLonsList();
                    for (List<Double> doubles : lonsList) {
                        for (Double lon : doubles) {
                            maxLon=Math.max(maxLon,lon);
                            minLon=Math.min(minLon,lon);
                        }
                    }
                    List<List<Double>> latsList = mapBorder.getLatsList();
                    for (List<Double> doubles : latsList) {
                        for (Double lat : doubles) {
                            maxLat=Math.max(maxLat,lat);
                            minLat=Math.min(minLat,lat);
                        }
                    }
                }
                mapPic.setStartLon(minLon);
                mapPic.setEndLon(maxLon);
                mapPic.setStartLat(maxLat);
                mapPic.setEndLat(minLat);
            }
        }
    }




}
