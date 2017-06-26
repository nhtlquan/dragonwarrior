package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.player.PlayerTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TemplateModule_ProvidePlayerTemplateFactory implements Factory<PlayerTemplate> {
  private final TemplateModule module;

  private final Provider<App> appProvider;

  public TemplateModule_ProvidePlayerTemplateFactory(
      TemplateModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public PlayerTemplate get() {
    return Preconditions.checkNotNull(
        module.providePlayerTemplate(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PlayerTemplate> create(TemplateModule module, Provider<App> appProvider) {
    return new TemplateModule_ProvidePlayerTemplateFactory(module, appProvider);
  }

  /** Proxies {@link TemplateModule#providePlayerTemplate(App)}. */
  public static PlayerTemplate proxyProvidePlayerTemplate(TemplateModule instance, App app) {
    return instance.providePlayerTemplate(app);
  }
}
