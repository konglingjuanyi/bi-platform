/**
 * Copyright (c) 2014 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package com.baidu.rigel.biplatform.tesseract.isservice.netty.service;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.rigel.biplatform.tesseract.netty.AbstractChannelInboundHandler;
import com.baidu.rigel.biplatform.tesseract.netty.message.AbstractMessage;
import com.baidu.rigel.biplatform.tesseract.netty.message.NettyAction;
import com.baidu.rigel.biplatform.tesseract.netty.message.isservice.SearchResultMessage;
import com.baidu.rigel.biplatform.tesseract.netty.message.isservice.ServerExceptionMessage;
import com.baidu.rigel.biplatform.tesseract.util.isservice.LogInfoConstants;

/**
 * SearchClientHandler
 * 
 * @author lijin
 *
 */
public class SearchClientHandler extends AbstractChannelInboundHandler {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchClientHandler.class);
    /**
     * ACTION_SUPPORT
     */
    private static final NettyAction ACTION_SUPPORT = NettyAction.NETTY_ACTION_SEARCH_FEEDBACK;
    /**
     * ACTION_FEEDBACK
     */
    private static final NettyAction ACTION_FEEDBACK = NettyAction.NETTY_ACTION_NULL;
    /**
     * message
     */
    private volatile AbstractMessage message;
    
    /**
     * 
     */
    public SearchClientHandler() {
        super(ACTION_SUPPORT, ACTION_FEEDBACK);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.baidu.rigel.biplatform.tesseract.netty.AbstractChannelInboundHandler
     * #messageReceived(io.netty.channel.ChannelHandlerContext,
     * java.lang.Object)
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info(String.format(LogInfoConstants.INFO_PATTERN_FUNCTION_BEGIN, "messageReceived",
            msg));
        if (msg instanceof SearchResultMessage) {
            message = (SearchResultMessage) msg;
        } else {
            message = (ServerExceptionMessage) msg;
            LOGGER.info(String.format(LogInfoConstants.INFO_PATTERN_FUNCTION_EXCEPTION,
                "messageReceived", msg));
        }
        LOGGER.info(String.format(LogInfoConstants.INFO_PATTERN_FUNCTION_END, "messageReceived",
            msg));
        ctx.close();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.baidu.rigel.biplatform.tesseract.netty.AbstractChannelInboundHandler
     * #getMessage()
     */
    
    @SuppressWarnings("unchecked")
    @Override
    public AbstractMessage getMessage() {
        return this.message;
    }
    
}