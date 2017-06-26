package vn.lequan.warrior.config.component;

import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.Main;
import vn.lequan.warrior.Main_MembersInjector;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.config.module.AppModule_ProvideAppFactory;
import vn.lequan.warrior.config.module.ButtonModule;
import vn.lequan.warrior.config.module.ButtonModule_ProvideCreditsButtonFactory;
import vn.lequan.warrior.config.module.ButtonModule_ProvideMenuViewButtonsFactory;
import vn.lequan.warrior.config.module.ButtonModule_ProvideNewGameButtonFactory;
import vn.lequan.warrior.config.module.ButtonModule_ProvideOkButtonFactory;
import vn.lequan.warrior.config.module.ButtonModule_ProvidePlayViewButtonsFactory;
import vn.lequan.warrior.config.module.ButtonModule_ProvideScoreButtonFactory;
import vn.lequan.warrior.config.module.FeatureModule;
import vn.lequan.warrior.config.module.FeatureModule_ProvideDebugBox2DFactory;
import vn.lequan.warrior.config.module.FeatureModule_ProvideDebugFPSFactory;
import vn.lequan.warrior.config.module.ViewModule;
import vn.lequan.warrior.config.module.ViewModule_ProvideMenuViewFactory;
import vn.lequan.warrior.config.module.ViewModule_ProvidePlayViewFactory;
import vn.lequan.warrior.features.debug.DebugBox2D;
import vn.lequan.warrior.features.debug.DebugFPS;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.menu.MenuInteractorImpl;
import vn.lequan.warrior.views.menu.MenuInteractorImpl_MembersInjector;
import vn.lequan.warrior.views.menu.MenuView;
import vn.lequan.warrior.views.play.PlayInteractorImpl;
import vn.lequan.warrior.views.play.PlayInteractorImpl_MembersInjector;
import vn.lequan.warrior.views.play.PlayView;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerNavigationComponent implements NavigationComponent {
  private Provider<App> provideAppProvider;

  private Provider<DebugFPS> provideDebugFPSProvider;

  private Provider<DebugBox2D> provideDebugBox2DProvider;

  private Provider<MenuButton> provideNewGameButtonProvider;

  private Provider<MenuButton> provideScoreButtonProvider;

  private Provider<MenuButton> provideCreditsButtonProvider;

  private Provider<MenuButton[]> provideMenuViewButtonsProvider;

  private Provider<MenuView> provideMenuViewProvider;

  private MembersInjector<Main> mainMembersInjector;

  private Provider<MenuButton> provideOkButtonProvider;

  private Provider<MenuButton[]> providePlayViewButtonsProvider;

  private Provider<PlayView> providePlayViewProvider;

  private MembersInjector<MenuInteractorImpl> menuInteractorImplMembersInjector;

  private MembersInjector<PlayInteractorImpl> playInteractorImplMembersInjector;

  private DaggerNavigationComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static NavigationComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideAppProvider =
        DoubleCheck.provider(AppModule_ProvideAppFactory.create(builder.appModule));

    this.provideDebugFPSProvider =
        DoubleCheck.provider(
            FeatureModule_ProvideDebugFPSFactory.create(builder.featureModule, provideAppProvider));

    this.provideDebugBox2DProvider =
        DoubleCheck.provider(
            FeatureModule_ProvideDebugBox2DFactory.create(
                builder.featureModule, provideAppProvider));

    this.provideNewGameButtonProvider =
        ButtonModule_ProvideNewGameButtonFactory.create(builder.buttonModule, provideAppProvider);

    this.provideScoreButtonProvider =
        ButtonModule_ProvideScoreButtonFactory.create(builder.buttonModule, provideAppProvider);

    this.provideCreditsButtonProvider =
        ButtonModule_ProvideCreditsButtonFactory.create(builder.buttonModule, provideAppProvider);

    this.provideMenuViewButtonsProvider =
        ButtonModule_ProvideMenuViewButtonsFactory.create(
            builder.buttonModule,
            provideAppProvider,
            provideNewGameButtonProvider,
            provideScoreButtonProvider,
            provideCreditsButtonProvider);

    this.provideMenuViewProvider =
        ViewModule_ProvideMenuViewFactory.create(
            builder.viewModule, provideAppProvider, provideMenuViewButtonsProvider);

    this.mainMembersInjector =
        Main_MembersInjector.create(
            provideDebugFPSProvider, provideDebugBox2DProvider, provideMenuViewProvider);

    this.provideOkButtonProvider =
        ButtonModule_ProvideOkButtonFactory.create(builder.buttonModule, provideAppProvider);

    this.providePlayViewButtonsProvider =
        ButtonModule_ProvidePlayViewButtonsFactory.create(
            builder.buttonModule, provideOkButtonProvider);

    this.providePlayViewProvider =
        ViewModule_ProvidePlayViewFactory.create(
            builder.viewModule, provideAppProvider, providePlayViewButtonsProvider);

    this.menuInteractorImplMembersInjector =
        MenuInteractorImpl_MembersInjector.create(providePlayViewProvider);

    this.playInteractorImplMembersInjector =
        PlayInteractorImpl_MembersInjector.create(provideMenuViewProvider);
  }

  @Override
  public void inject(Main main) {
    mainMembersInjector.injectMembers(main);
  }

  @Override
  public void inject(MenuInteractorImpl interactor) {
    menuInteractorImplMembersInjector.injectMembers(interactor);
  }

  @Override
  public void inject(PlayInteractorImpl interactor) {
    playInteractorImplMembersInjector.injectMembers(interactor);
  }

  public static final class Builder {
    private AppModule appModule;

    private FeatureModule featureModule;

    private ButtonModule buttonModule;

    private ViewModule viewModule;

    private Builder() {}

    public NavigationComponent build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      if (featureModule == null) {
        this.featureModule = new FeatureModule();
      }
      if (buttonModule == null) {
        this.buttonModule = new ButtonModule();
      }
      if (viewModule == null) {
        this.viewModule = new ViewModule();
      }
      return new DaggerNavigationComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder featureModule(FeatureModule featureModule) {
      this.featureModule = Preconditions.checkNotNull(featureModule);
      return this;
    }

    public Builder viewModule(ViewModule viewModule) {
      this.viewModule = Preconditions.checkNotNull(viewModule);
      return this;
    }

    public Builder buttonModule(ButtonModule buttonModule) {
      this.buttonModule = Preconditions.checkNotNull(buttonModule);
      return this;
    }
  }
}
