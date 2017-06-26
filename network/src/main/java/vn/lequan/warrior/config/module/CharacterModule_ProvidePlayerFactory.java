package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.entity.player.PlayerTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CharacterModule_ProvidePlayerFactory implements Factory<Player> {
  private final CharacterModule module;

  private final Provider<App> appProvider;

  private final Provider<PlayerTemplate> templateProvider;

  public CharacterModule_ProvidePlayerFactory(
      CharacterModule module,
      Provider<App> appProvider,
      Provider<PlayerTemplate> templateProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert templateProvider != null;
    this.templateProvider = templateProvider;
  }

  @Override
  public Player get() {
    return Preconditions.checkNotNull(
        module.providePlayer(appProvider.get(), templateProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Player> create(
      CharacterModule module,
      Provider<App> appProvider,
      Provider<PlayerTemplate> templateProvider) {
    return new CharacterModule_ProvidePlayerFactory(module, appProvider, templateProvider);
  }

  /** Proxies {@link CharacterModule#providePlayer(App, PlayerTemplate)}. */
  public static Player proxyProvidePlayer(
      CharacterModule instance, App app, PlayerTemplate template) {
    return instance.providePlayer(app, template);
  }
}
