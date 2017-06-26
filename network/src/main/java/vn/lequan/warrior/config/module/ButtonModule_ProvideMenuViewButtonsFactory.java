package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ButtonModule_ProvideMenuViewButtonsFactory implements Factory<MenuButton[]> {
  private final ButtonModule module;

  private final Provider<App> appProvider;

  private final Provider<MenuButton> newGameButtonProvider;

  private final Provider<MenuButton> scoreButtonProvider;

  private final Provider<MenuButton> creditsButtonProvider;

  public ButtonModule_ProvideMenuViewButtonsFactory(
      ButtonModule module,
      Provider<App> appProvider,
      Provider<MenuButton> newGameButtonProvider,
      Provider<MenuButton> scoreButtonProvider,
      Provider<MenuButton> creditsButtonProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert newGameButtonProvider != null;
    this.newGameButtonProvider = newGameButtonProvider;
    assert scoreButtonProvider != null;
    this.scoreButtonProvider = scoreButtonProvider;
    assert creditsButtonProvider != null;
    this.creditsButtonProvider = creditsButtonProvider;
  }

  @Override
  public MenuButton[] get() {
    return Preconditions.checkNotNull(
        module.provideMenuViewButtons(
            appProvider.get(),
            newGameButtonProvider.get(),
            scoreButtonProvider.get(),
            creditsButtonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MenuButton[]> create(
      ButtonModule module,
      Provider<App> appProvider,
      Provider<MenuButton> newGameButtonProvider,
      Provider<MenuButton> scoreButtonProvider,
      Provider<MenuButton> creditsButtonProvider) {
    return new ButtonModule_ProvideMenuViewButtonsFactory(
        module, appProvider, newGameButtonProvider, scoreButtonProvider, creditsButtonProvider);
  }

  /**
   * Proxies {@link ButtonModule#provideMenuViewButtons(App, MenuButton, MenuButton, MenuButton)}.
   */
  public static MenuButton[] proxyProvideMenuViewButtons(
      ButtonModule instance,
      App app,
      MenuButton newGameButton,
      MenuButton scoreButton,
      MenuButton creditsButton) {
    return instance.provideMenuViewButtons(app, newGameButton, scoreButton, creditsButton);
  }
}
