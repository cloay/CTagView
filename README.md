CTagView
========

A costum view display tags.

####How to use
1 Add CTagView in layout<br>
```xml
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ffffff"
    android:orientation="vertical">
    <TextView android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:layout_margin="10dp"
        android:textSize="24sp"
        android:text="标签:"/>
    <com.cloay.ctagviewdemo.widget.CTagView 
        android:id="@+id/tag_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        />
</LinearLayout>
```
2 And add the following code<br>
```java
    String[] tags = {"音乐", "文艺范", "科幻", "二逼青年", "搞笑", "清新", "猴子请来的逗比", "NBA", "卡哇伊", "电影", "自定义标签"};
	CTagView tagView = (CTagView) findViewById(R.id.tag_view);
	tagView.setOnTagClickListener(this);
	tagView.setTags(tags);
```

<img src="https://raw.githubusercontent.com/cloay/CTagView/master/tag.png" width="360" height="640"/>




