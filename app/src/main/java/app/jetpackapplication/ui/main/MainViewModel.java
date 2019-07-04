package app.jetpackapplication.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
