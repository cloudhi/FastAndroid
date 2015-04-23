# Fast Android

### 混淆配置
在使用AutoView时，需要配置以下内容：

    -keepclassmembers class * {
        @com.github.yoojia.fast.view.AutoView *;
    }