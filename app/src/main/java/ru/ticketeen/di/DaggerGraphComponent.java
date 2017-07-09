package ru.ticketeen.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.ticketeen.App;
import ru.ticketeen.viewmodel.LoginViewModel;

@Singleton
@Component(modules = {MainModule.class})
public interface DaggerGraphComponent {

    void inject(LoginViewModel viewModel);

    final class Initializer {
        private Initializer() {
        }

        public static DaggerGraphComponent init(App app) {
            return DaggerDaggerGraphComponent.builder()
                    .mainModule(new MainModule(app))
                    .build();
        }
    }
}
