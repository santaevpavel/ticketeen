package ru.ticketeen;


import javax.inject.Singleton;

import dagger.Component;
import ru.ticketeen.di.DaggerGraphComponent;

@Singleton
@Component(modules = TestMainModule.class)
public interface TestComponent extends DaggerGraphComponent {

    final class Initializer {
        private Initializer() {
        }

        public static TestComponent init(App app) {
            return DaggerTestComponent.builder()
                    .testMainModule(new TestMainModule(app))
                    .build();
        }
    }
}
