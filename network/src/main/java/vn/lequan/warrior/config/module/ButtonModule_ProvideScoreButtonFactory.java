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
public final class ButtonModule_ProvideScoreButtonFactory implements Factory<MenuButton> {
  private final ButtonModule module;

  private final Provider<App> appProvider;

  public ButtonModule_ProvideScoreButtonFactory(ButtonModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public MenuButton get() {
    return Preconditions.checkNotNull(
        module.provideScoreButton(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MenuButton> create(ButtonModule module, Provider<App> appProvider) {
    return new ButtonModule_ProvideScoreButtonFactory(module, appProvider);
  }

  /** Proxies {@link ButtonModule#provideScoreButton(App)}. */
  public static MenuButton proxyProvideScoreButton(ButtonModule instance, App app) {
    return instance.provideScoreButton(app);
  }
}
