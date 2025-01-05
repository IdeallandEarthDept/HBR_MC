package com.deeplake.hbr_mc.designs.danmaku.basic;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.MessageDef;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final ISite site;
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker, ISite site) {
        this.handshaker = handshaker;
        this.site = site;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if (ModConfig.CONFIG.LOG_ON) {
            System.out.println("WebSocket client connected to server.");
        }
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketClientHandshaker) {
            WebSocketClientHandshaker handshaker = (WebSocketClientHandshaker) evt;
            System.out.println("Sending WebSocket handshake request with headers: " + handshaker.toString());
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Main.Log(ctx.channel().remoteAddress().toString() + " disconnected!");
        CommonFunctions.broadcastbykey(MessageDef.MSG_LIVE_CLOSED);
        if (InitDanmaku.HEART_BEAT_TASK != null && !InitDanmaku.HEART_BEAT_TASK.isCancelled())
        {
            InitDanmaku.HEART_BEAT_TASK.cancel(true);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        if (!handshaker.isHandshakeComplete()) {
            handshaker.finishHandshake(ch, (FullHttpResponse) msg);
            Main.Log("WebSocket Client connected!");
            handshakeFuture.setSuccess();
            return;
        }

        if (msg instanceof FullHttpResponse) {
            final FullHttpResponse response = (FullHttpResponse) msg;
            throw new Exception("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content="
                    + response.content().toString(CharsetUtil.UTF_8) + ')');
        }

        final WebSocketFrame frame = (WebSocketFrame) msg;
        site.handMessage(frame);
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println("Exception caught during WebSocket handshake or communication: " + cause.getMessage());
        cause.printStackTrace();
        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }
        ctx.close();
        try {
            InitDanmaku.WEBSOCKET_CLIENT.close();
            InitDanmaku.WEBSOCKET_CLIENT = null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
