<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etServerUrl"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="WebSocket 地址 (如 ws://192.168.1.100:8765)"
        android:inputType="textUri"
        android:padding="12dp"/>

    <Button
        android:id="@+id/btnStart"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="开始捕获"
        android:layout_marginTop="16dp"/>

    <Button
        android:id="@+id/btnStop"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="停止"
        android:enabled="false"
        android:layout_marginTop="8dp"/>

    <TextView
        android:id="@+id/tvStatus"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="状态：未连接"
        android:padding="8dp"
        android:layout_marginTop="16dp"/>
</LinearLayout>
