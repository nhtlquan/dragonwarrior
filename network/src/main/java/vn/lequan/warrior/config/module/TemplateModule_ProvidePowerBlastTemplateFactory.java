package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.projectiles.PowerBlastTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TemplateModule_ProvidePowerBlastTemplateFactory
    implements Factory<PowerBlastTemplate> {
  private final TemplateModule module;

  private final Provider<App> appProvider;

  public TemplateModule_ProvidePowerBlastTemplateFactory(
      TemplateModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public PowerBlastTemplate get() {
    return Preconditions.checkNotNull(
        module.providePowerBlastTemplate(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PowerBlastTemplate> create(
      TemplateModule module, Provider<App> appProvider) {
    return new TemplateModule_ProvidePowerBlastTemplateFactory(module, appProvider);
  }

  /** Proxies {@link TemplateModule#providePowerBlastTemplate(App)}. */
  public static PowerBlastTemplate proxyProvidePowerBlastTemplate(
      TemplateModule instance, App app) {
    return instance.providePowerBlastTemplate(app);
  }
}
