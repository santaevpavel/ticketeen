package ru.ticketeen.view.activity;


import ru.ticketeen.App;
import ru.ticketeen.TestComponent;
import ru.ticketeen.di.DaggerGraphComponent;

public class TestApp extends App {

    @Override
    protected DaggerGraphComponent buildComponentGraph() {
        return TestComponent.Initializer.init(this);
    }
}
