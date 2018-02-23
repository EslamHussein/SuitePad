# SuitePad Android Sample Task

For this sample task, we’re asking you to create a restaurant menu viewing software that could exist in the context of our SuitePad tablet.  In this Task, you are targetting 3 main concerns, which should correspond to a final output of 3 separate (micro) Android Applications.

## Documentation
In this repo you will find **Four Modules** in **One Android Project**

1. **Commanconstant** 
2. **DataSource**
3. **HTTPProxyServer**
4. **Presentation**


## 1. Commanconstant 

This module is a common module have content provider Constance like __AUTHORITY__ , __BASE\_CONTENT\_URI__ , __COLUMN\_UUID__,__MenuEntryIndex__ and common DTOs.
I used this module in DataSource and HTTPProxyServer modules.



### Example:
			 
		public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
		public static final String COLUMN_UUID = "UUID";
		public static final String PATH_MENU_ITEMS = "menu";

### How To Use?
Add this line to your Dependencies.

	dependencies {
		...
	    implementation project(':commanconstant')
	    ...
	}


## 2. DataSource 
This Module has Content Provider to share the date with other apps and I saved the data as hardcoded but if the project scalable I suggest to create a database to store the data. I implemented only query operation, for others operations and the wrong URI, I throw unsupported operation / URI  exception. We can create a permission for using the content provider for more Security. 


### How To Use?

	Uri menuContentProviderUri = MenuContract.MenuEntry.CONTENT_URI; // you will find this Uri in commanconstant Module
	Cursor menuItemsCursor = App.get().getContentResolver().query(menuContentProviderUri, null,
                        null, null, null);



## 3. HTTPProxyServer 

I created the service have full responsibility to start and stop the HTTP server and it via Intent and small presenter and repo to handle getting the data from the content provider.


### Libraries : 
I used some of libraries to implement this module :

1. [AndroidAsync](https://github.com/koush/AndroidAsync) to create http server. 
2. [Gson](https://github.com/google/gson) to serilize the data to Json.
3. [RxJava & RxAndroid](https://github.com/ReactiveX/RxAndroid) to handel getting data from content provier in background thread 
4. Commanconstant to use The constant of content provider and avoid copy & paste mistacts :)
 

### How To Use?

To **Start** the server to can use the code below:

	Intent intent = new Intent();
	intent.setComponent(new ComponentName("com.suitepad.httpproxyserver","com.suitepad.httpproxyserver.HTTPProxyService"));
    startService(intent);
    

To **Stop** the server to can use the code below:
	
	 Intent intent = new Intent();
	 intent.setComponent(new ComponentName("com.suitepad.httpproxyserver", "com.suitepad.httpproxyserver.HTTPProxyService"));
     stopService(intent);


## 3. Presentation 
This module has an activity ( Webview ) to show the webpage stored in Assets folder and I implemented `CustomWebViewClient` to intercept webview requests  and if the remote URL `http://someremoteurl.com/sample.json` request failed I will get the cached data from local URL `http://localhost:8080/file.json` 

### Libraries : 

1. [Okhttp](http://square.github.io/okhttp/) to handle API REST calls

## Note
The DataSource , HTTPProxyServer and Presentation all of them under the same

	android:sharedUserId = "com.suitepad"


## Build Requirements

This is an Android Studio project using the Gradle build system, to build this project you need

1. Build tools version 27.0.2
2. API level 26
3. Android Studio 3 
4. Gradel Version 3

