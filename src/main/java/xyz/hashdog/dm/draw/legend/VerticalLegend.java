package xyz.hashdog.dm.draw.legend;

import xyz.hashdog.dm.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class VerticalLegend extends  PieceLegend{
    protected VerticalLegend(List<String> levelColor, List<String> levelHatches, List<String> levelLabel) {
        super(levelColor, levelHatches, levelLabel);
    }

    protected VerticalLegend(List<String> levelColor, List<String> levelLabel) {
        super(levelColor, levelLabel);
    }
    @Override
    public BufferedImage create() {
        Font font = new Font(this.font, Font.PLAIN, this.fontSize);
        int maxLabelWidth = ImageUtil.labelsMaxWidth(font, levelLabel);
        int maxLabelHight = ImageUtil.highByFont(font);

        int legWidth = colorPieceWidth + maxLabelWidth + this.space+Math.abs(offsetX);
        int legHight = colorPieceHight * levelColor.size()+1+Math.abs(offsetY);

        BufferedImage lenImg = ImageUtil.getTransparentImg(legWidth, legHight);
        Graphics2D graphics = lenImg.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 0;
        int y = offsetY>=0?0:Math.abs(offsetY);
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
                        graphics.drawLine(x+(n*wi),y,x,y+(n*hi));
                        // draw right
                        graphics.drawLine(x+colorPieceWidth,y+(n*hi),x+(n*wi),y+colorPieceHight);
                    }
                }
            }
            graphics.setColor(Color.black);
            graphics.drawRoundRect(x, y, colorPieceWidth, colorPieceHight, 0, 0);
            graphics.setFont(font);
            graphics.drawString(levelLabel.get(i), colorPieceWidth + this.space+this.offsetX, y + (colorPieceHight / 2) + (maxLabelHight / 4)+offsetY);

            y += colorPieceHight;
        }
        graphics.dispose();
        BufferedImage img = drawTitle(lenImg);
        return img;
    }

    @Override
    protected BufferedImage joinTitle(BufferedImage lenImg,BufferedImage titleImg) {
        int tw = titleImg.getWidth();
        int th = titleImg.getHeight();
        int mw = Math.max(tw, lenImg.getWidth());
        int mh = th+lenImg.getHeight()+titleSpace;
        BufferedImage mimg = ImageUtil.getTransparentImg(mw, mh);
        Graphics2D mg = mimg.createGraphics();
        mg.drawImage(titleImg,(mw-tw)/2,0,null);
        mg.drawImage(lenImg,(mw-lenImg.getWidth())/2,th+titleSpace,null);
        mg.dispose();
        return mimg;
    }


}
