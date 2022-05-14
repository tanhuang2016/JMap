package xyz.hashdog.dm.draw.legend;


import xyz.hashdog.dm.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class HorizontalLegend extends PieceLegend {
    protected HorizontalLegend(List<String> levelColor, List<String> levelHatches, List<String> levelLabel) {
        super(levelColor, levelHatches, levelLabel);
    }

    protected HorizontalLegend(List<String> levelColor, List<String> levelLabel) {
        super(levelColor, levelLabel);
    }

    {
        fontSize = 20;
        colorPieceWidth=48;
        colorPieceHight=22;
        titleFontSize=22;
    }
    @Override
    public BufferedImage create() {
        Font font = new Font(this.font, Font.PLAIN, this.fontSize);
        int maxLabelHight = ImageUtil.highByFont(font);

        int legWidth = colorPieceWidth * levelColor.size()+1+Math.abs(offsetX);
        int legHight = colorPieceHight +maxLabelHight + this.space+1+Math.abs(offsetY);

        BufferedImage lenImg = ImageUtil.getTransparentImg(legWidth, legHight);
        Graphics2D graphics = lenImg.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = offsetX>=0?0:Math.abs(offsetX);
        int y = this.space+maxLabelHight;
        for (int i = 0; i < levelColor.size(); i++) {
            Color fillColor=Color.decode(levelColor.get(i));
            if(!"".equals(levelHatches.get(i))){
                fillColor=Color.WHITE;
            }
            graphics.setColor(fillColor);
            graphics.fillRoundRect(x, y, colorPieceWidth, colorPieceHight, 0, 0);
            if(!"".equals(levelHatches.get(i))){
                graphics.setColor(Color.decode(levelColor.get(i)));
                if("///".equals(levelHatches.get(i))){
                    int wi= colorPieceWidth/5;
                    int hi= colorPieceHight/5;
                    int num = colorPieceWidth/wi;
                    for (int n = 0; n < num; n++) {
                        // draw left
                        graphics.drawLine(x,y+(n*hi),x+(n*wi),y);
                        // draw right
                        graphics.drawLine(x+(n*wi),y+colorPieceHight,x+colorPieceWidth,y+(n*hi));
                    }
                }
            }
            graphics.setColor(Color.black);
            graphics.drawRoundRect(x, y, colorPieceWidth, colorPieceHight, 0, 0);
            graphics.setFont(font);
            int fw = ImageUtil.widthByString(font, levelLabel.get(i));
            graphics.drawString(levelLabel.get(i), x + ((colorPieceWidth-fw) / 2) +offsetX,colorPieceHight +this.offsetY);

            x += colorPieceWidth;
        }
        graphics.dispose();
        BufferedImage img = drawTitle(lenImg);
        return img;
    }

    @Override
    protected BufferedImage joinTitle(BufferedImage lenImg,BufferedImage titleImg) {
        int tw = titleImg.getWidth();
        int th = titleImg.getHeight();
        int h=lenImg.getHeight()-this.colorPieceHight;
        int mw = tw+lenImg.getWidth()+titleSpace;
        int mh = Math.max(th, lenImg.getHeight());
        BufferedImage mimg = ImageUtil.getTransparentImg(mw, mh);
        Graphics2D mg = mimg.createGraphics();
        ///对整个图例高度垂直居中
//        mg.drawImage(titleImg,0,(mh-th)/2,null);
        ///对色块垂直居中
        mg.drawImage(titleImg,0,h+(this.colorPieceHight-th)/2,null);
        mg.drawImage(lenImg,tw+titleSpace,(mh-lenImg.getHeight())/2,null);
        mg.dispose();
        return mimg;
    }
}
