package com.ntsky.bbs.webfeed.element;

import com.ntsky.bbs.webfeed.ElementException;

public interface AtomElement extends ElementIF{

    /**
     * 订阅Atom
     * @param document
     * @param feed
     */
    public void feedAtom(Feed feed, String destXMLFile) throws ElementException;
    
}
