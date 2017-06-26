package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.ui.buttons.MenuButton;
import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.play.PlayView;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ViewModule_ProvidePlayViewFactory implements Factory<PlayView> {
  private final ViewModule module;

  private final Provider<App> appProvider;

  private final Provider<MenuButton[]> buttonsProvider;

  public ViewModule_ProvidePlayViewFactory(
      ViewModule module, Provider<App> appProvider, Provider<MenuButton[]> buttonsProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert buttonsProvider != null;
    this.buttonsProvider = buttonsProvider;
  }

  @Override
  public PlayView get() {
    return Preconditions.checkNotNull(
        module.providePlayView(appProvider.get(), buttonsProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PlayView> create(
      ViewModule module, Provider<App> appProvider, Provider<MenuButton[]> buttonsProvider) {
    return new ViewModule_ProvidePlayViewFactory(module, appProvider, buttonsProvider);
  }

  /** Proxies {@link ViewModule#providePlayView(App, MenuButton[])}. */
  public static PlayView proxyProvidePlayView(ViewModule instance, App app, MenuButton[] buttons) {
    return instance.providePlayView(app, buttons);
  }
}
