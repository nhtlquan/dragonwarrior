package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.ui.buttons.MenuButton;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ButtonModule_ProvidePlayViewButtonsFactory implements Factory<MenuButton[]> {
  private final ButtonModule module;

  private final Provider<MenuButton> okButtonProvider;

  public ButtonModule_ProvidePlayViewButtonsFactory(
      ButtonModule module, Provider<MenuButton> okButtonProvider) {
    assert module != null;
    this.module = module;
    assert okButtonProvider != null;
    this.okButtonProvider = okButtonProvider;
  }

  @Override
  public MenuButton[] get() {
    return Preconditions.checkNotNull(
        module.providePlayViewButtons(okButtonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MenuButton[]> create(
      ButtonModule module, Provider<MenuButton> okButtonProvider) {
    return new ButtonModule_ProvidePlayViewButtonsFactory(module, okButtonProvider);
  }

  /** Proxies {@link ButtonModule#providePlayViewButtons(MenuButton)}. */
  public static MenuButton[] proxyProvidePlayViewButtons(
      ButtonModule instance, MenuButton okButton) {
    return instance.providePlayViewButtons(okButton);
  }
}
