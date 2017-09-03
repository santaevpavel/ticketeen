package ru.ticketeen;


import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import ru.ticketeen.view.activity.TestApp;

public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApp.class.getName(), context);
    }
}