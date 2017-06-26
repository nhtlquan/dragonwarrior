package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.menu.MenuView;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ViewModule_ProvideMenuViewFactory implements Factory<MenuView> {
  private final ViewModule module;

  private final Provider<App> appProvider;

  private final Provider<MenuButton[]> buttonsProvider;

  public ViewModule_ProvideMenuViewFactory(
      ViewModule module, Provider<App> appProvider, Provider<MenuButton[]> buttonsProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert buttonsProvider != null;
    this.buttonsProvider = buttonsProvider;
  }

  @Override
  public MenuView get() {
    return Preconditions.checkNotNull(
        module.provideMenuView(appProvider.get(), buttonsProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MenuView> create(
      ViewModule module, Provider<App> appProvider, Provider<MenuButton[]> buttonsProvider) {
    return new ViewModule_ProvideMenuViewFactory(module, appProvider, buttonsProvider);
  }

  /** Proxies {@link ViewModule#provideMenuView(App, MenuButton[])}. */
  public static MenuView proxyProvideMenuView(ViewModule instance, App app, MenuButton[] buttons) {
    return instance.provideMenuView(app, buttons);
  }
}
