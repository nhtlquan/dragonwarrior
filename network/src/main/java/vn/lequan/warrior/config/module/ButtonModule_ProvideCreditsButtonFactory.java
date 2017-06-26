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
public final class ButtonModule_ProvideCreditsButtonFactory implements Factory<MenuButton> {
  private final ButtonModule module;

  private final Provider<App> appProvider;

  public ButtonModule_ProvideCreditsButtonFactory(ButtonModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public MenuButton get() {
    return Preconditions.checkNotNull(
        module.provideCreditsButton(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MenuButton> create(ButtonModule module, Provider<App> appProvider) {
    return new ButtonModule_ProvideCreditsButtonFactory(module, appProvider);
  }

  /** Proxies {@link ButtonModule#provideCreditsButton(App)}. */
  public static MenuButton proxyProvideCreditsButton(ButtonModule instance, App app) {
    return instance.provideCreditsButton(app);
  }
}
