package com.amazon.creturns.rex.voc.widget.fivestar;

import com.amazon.creturns.rex.voc.widget.WidgetProcessor;

public interface FiveStarProcessor extends WidgetProcessor<FiveStar> {
    /**
     * This method process the passed widget & returns the processed widget
     * @param widget object of FiveStar class
     * @return the processed FiveStar widget
     */
    FiveStar process(FiveStar widget);
}
