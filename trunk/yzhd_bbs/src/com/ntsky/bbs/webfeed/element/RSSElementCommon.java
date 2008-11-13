package com.ntsky.bbs.webfeed.element;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ntsky.bbs.webfeed.Globals;
import com.ntsky.bbs.webfeed.DOMData;

public class RSSElementCommon {

    private static Logger __logger = Logger.getLogger(RSSElementCommon.class);

    /**
     * input
     * 
     * @param document
     * @param parElement
     * @param textinput
     */
    protected void addTextInput(DOMData domData, Element parElement,
            TextInput textinput) {
        Element eTextInput = domData.addElement(parElement, Globals.TEXTINPUT);
        domData.addTextElement(eTextInput, Globals.TEXTINPUT_TITLE, textinput
                .getTitle());
        domData.addTextElement(eTextInput, Globals.TEXTINPUT_DESCRIPTION,
                textinput.getDescription());
        domData.addTextElement(eTextInput, Globals.TEXTINPUT_NAME, textinput
                .getName());
        domData.addTextElement(eTextInput, Globals.TEXTINPUT_LINK, textinput
                .getLink());
    }

    /**
     * image
     * 
     * @param document
     * @param parElement
     * @param image
     */
    protected void addImage(DOMData domData, Element parElement, Image image) {
        Element eImage = domData.addElement(parElement, Globals.IMAGE);
        domData.addTextElement(eImage, Globals.IMAGE_TITLE, image.getTitle());
        domData.addTextElement(eImage, Globals.IMAGE_URL, image.getTitle());
        domData.addTextElement(eImage, Globals.IMAGE_LINK, image.getLink());
        domData.addTextElement(eImage, Globals.IMAGE_WIDTH, image.getWidth());
        domData.addTextElement(eImage, Globals.IMAGE_HEIGHT, image.getHeight());
        domData.addTextElement(eImage, Globals.IMAGE_DESCRIPTION, image
                .getDescription());
    }
}