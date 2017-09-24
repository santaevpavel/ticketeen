package ru.ticketeen.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.ticketeen.App;
import ru.ticketeen.view.activity.LoginActivity;
import ru.ticketeen.view.activity.MainActivity;
import ru.ticketeen.viewmodel.LoginViewModel;
import ru.ticketeen.viewmodel.TicketListViewModel;

@Singleton
@Component(modules = {MainModule.class})
public interface DaggerGraphComponent {

    void inject(LoginViewModel viewModel);

    void inject(LoginActivity loginActivity);

    void inject(TicketListViewModel ticketListViewModel);

    void inject(MainActivity mainActivity);

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
