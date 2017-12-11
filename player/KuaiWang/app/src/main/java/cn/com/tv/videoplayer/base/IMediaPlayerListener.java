package cn.com.tv.videoplayer.base;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 播放器监听
 */
public interface IMediaPlayerListener extends IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener {
}
