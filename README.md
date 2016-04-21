# EdittextLibrary   

##PhoneEditText
 
用法：    
 project的build.gradle      
 
 
 	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}


module的build.gradle    


   	dependencies {
	     compile 'com.github.messnoTrace:EditTextLibrary:v1.0'
	}   
	
	
	
	
	一个电话号码格式化的输入框       
	
	
	<com.notrace.library.PhoneEdittext
        android:id="@+id/editTextMobileNo"
        android:layout_width="match_parent"
        android:layout_height="30dp"
        android:background="#fff"
        android:gravity="center_vertical"
        android:hint="输入手机号码"
        android:textSize="15sp"
        android:textColor="#f00"
        android:textColorHint="#ff0"
        android:inputType="number"
        android:paddingLeft="15dp"
        android:paddingRight="15dp"
        android:maxLength="13"
        android:singleLine="true" />
        
        
        
    身份证号码输入框
    
    
    
      <com.notrace.library.IDCardEditText
        android:id="@+id/editTextMobileNo"
        android:layout_width="match_parent"
        android:layout_height="30dp"
        android:background="#fff"
        android:gravity="center_vertical"
        android:hint="输入手机号码"
        android:textSize="15sp"
        android:textColor="#f00"
        android:textColorHint="#ff0"
        android:inputType="number"
        android:paddingLeft="15dp"
        android:paddingRight="15dp"
        android:maxLength="13"
        android:singleLine="true" />


   
   
   
