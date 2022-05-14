package xyz.hashdog.dm.draw;

import xyz.hashdog.dm.util.ImageUtil;

import java.awt.image.BufferedImage;

public abstract class AbstractMap implements JMap{
    protected BufferedImage img;
    @Override
    public void out(String path) {
        beforeOut();
        ImageUtil. write(this.img,path);
    }

    @Override
    public byte[] outToBytes() {
        beforeOut();
        byte[] bytes = ImageUtil.imageToBytes(this.img);
        return bytes;
    }

    @Override
    public BufferedImage out() {
        beforeOut();
        return img;
    }
    private void beforeOut(){
        if(img==null){
            plot();
        }
    }
}
