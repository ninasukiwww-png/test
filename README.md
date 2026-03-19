# Android 音频捕获应用

这是一个能够实时捕获手机内部音频（QQ电话、媒体播放等声音），并通过WebSocket发送到指定服务器的Android应用。

## 功能特性

- ✅ 实时捕获Android 10+内部音频
- ✅ 通过WebSocket传输PCM音频数据
- ✅ 支持16kHz采样率、单声道、16-bit PCM格式
- ✅ 前台服务通知，防止被系统杀死
- ✅ 简单的用户界面

## 技术栈

- **语言**: Kotlin
- **最低SDK**: Android 10 (API 29)
- **音频捕获**: AudioPlaybackCapture API + MediaProjection
- **网络传输**: Java-WebSocket库
- **架构**: 前台服务 + ViewBinding + Coroutines

## 使用说明

1. **安装应用**: 使用Android Studio打开项目并运行
2. **配置服务器**: 输入WebSocket服务器地址（如 `ws://192.168.1.100:8765`）
3. **开始捕获**: 点击"开始捕获"按钮，授予屏幕录制权限
4. **停止捕获**: 点击"停止"按钮结束音频捕获

## 音频格式

- 采样率: 16kHz
- 声道: 单声道
- 编码: PCM 16-bit
- 数据流: 实时WebSocket二进制传输

## 权限说明

应用需要以下权限：
- `FOREGROUND_SERVICE`: 前台服务权限
- `RECORD_AUDIO`: 音频录制权限
- 屏幕录制权限（用户手动授权）

## 注意事项

1. 仅支持Android 10及以上版本
2. 需要用户手动授权屏幕录制
3. 音频捕获期间会显示前台通知
4. 确保WebSocket服务器正常运行

## 项目结构

```
app/src/main/java/com/example/audiocapture/
├── ui/
│   └── MainActivity.kt          # 主界面
├── service/
│   └── AudioCaptureService.kt   # 音频捕获服务
└── websocket/
    └── WebSocketManager.kt      # WebSocket客户端
```

## 开发说明

如需修改或扩展功能，请参考以下关键类：
- `AudioCaptureService`: 音频捕获核心逻辑
- `WebSocketManager`: WebSocket连接管理
- `MainActivity`: 用户界面和权限处理

## 许可证

MIT License
