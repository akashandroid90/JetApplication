package app.jetpackapplication.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> data;

    public MainViewModel(){
        data=new MutableLiveData<String>();
        data.setValue("Hello World");
    }

    public LiveData<String> getData(){
        return data;
    }
}
