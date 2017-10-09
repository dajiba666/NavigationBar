#### 1、 自定义头部导航栏功能，扩展性强。

#### 2、如果需要自己定义布局只需要继承AbsNavigationBar 这个类就行 参照我的DefauleNavigationBar写法就可以完成。

最简单的用法

```
new DefauleNavigationBar.Builder(this).setTitle("我是标题")
                .setBackClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"点击左边图片",Toast.LENGTH_SHORT).show();
                    }
                }).setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击右边",Toast.LENGTH_SHORT).show();
            }
        }).setRightText("右边文字").builer();
```
#### 3、如果有仔细看可能会有点疑惑，为什么不需要添加布局，因为我们的布局是上面还有两层布局嵌套一个是直接new出来的FrameLayout，然后在把系统的布局add进这个FrameLayout布局中，最后才是我们自己的布局。我们的布局就是放到content这个id的LinearLayout中 然后获取第0个位置 就是我们自己的布局了，最后在把这个标题栏add进第0个位置


```
private void createAndBindView(){

        if (mParams.mParent ==null){
            ViewGroup activityRoot = (ViewGroup)((Activity)mParams.mContext).findViewById(android.R.id.content);
            mParams.mParent = (ViewGroup) activityRoot.getChildAt(0);
        }

        if (mParams.mParent == null){

            return;
        }

        mNavigationView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(),mParams.mParent,false);

        mParams.mParent.addView(mNavigationView,0);

        applyView();
    }

```

