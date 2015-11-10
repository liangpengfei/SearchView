# SearchView
This a view that waiting for network connection to resolve with cool animation .
I was inspired by [this in dribbble](https://dribbble.com/shots/2341287-Search)

for the view has not implements all its animation , so I am glad for other people to help me to improve this view,this is so cool!

![screenshot](http://ww2.sinaimg.cn/large/005u6Kr2gw1exvt1c2wg7g30m80goaao.gif)

#Usage

XML


		<xhome.uestcfei.com.searchview.SearchView
        android:layout_width="300dp"
        android:id="@+id/search"
        android:layout_centerHorizontal="true"
        android:layout_height="300dp" />

JAVA

		public void stop(View view) {
      		 Log.d(TAG, "stop");
     		 searchView.stopSearch();
   		 }

   		 public void start(View view) {
     	     Log.d(TAG, "start");
     		 searchView.startSearch();
    	 }
    	 
    	 
    	 
#License
no license ,share as you like it !