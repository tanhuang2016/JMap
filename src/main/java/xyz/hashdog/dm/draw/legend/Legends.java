package xyz.hashdog.dm.draw.legend;

import java.util.List;

public class Legends {
    public static Legend createVerticalPieceLegend(List<String> levelColor, List<String> levelHatches, List<String> levelLabel){
        return new VerticalLegend(levelColor,levelHatches,levelLabel);
    }
    public static Legend createVerticalPieceLegend(List<String> levelColor, List<String> levelLabel){
        return new VerticalLegend(levelColor,levelLabel);
    }
    public static Legend createHorizontalPieceLegend(List<String> levelColor, List<String> levelHatches, List<String> levelLabel){
        return new HorizontalLegend(levelColor,levelHatches,levelLabel);
    }
    public static Legend createHorizontalPieceLegend(List<String> levelColor, List<String> levelLabel){
        return new HorizontalLegend(levelColor,levelLabel);
    }

}
