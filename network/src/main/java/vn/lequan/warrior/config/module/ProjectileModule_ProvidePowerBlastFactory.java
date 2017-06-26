package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.projectiles.PowerBlast;
import vn.lequan.warrior.entity.projectiles.PowerBlastTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ProjectileModule_ProvidePowerBlastFactory implements Factory<PowerBlast> {
  private final ProjectileModule module;

  private final Provider<App> appProvider;

  private final Provider<PowerBlastTemplate> templateProvider;

  public ProjectileModule_ProvidePowerBlastFactory(
      ProjectileModule module,
      Provider<App> appProvider,
      Provider<PowerBlastTemplate> templateProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert templateProvider != null;
    this.templateProvider = templateProvider;
  }

  @Override
  public PowerBlast get() {
    return Preconditions.checkNotNull(
        module.providePowerBlast(appProvider.get(), templateProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PowerBlast> create(
      ProjectileModule module,
      Provider<App> appProvider,
      Provider<PowerBlastTemplate> templateProvider) {
    return new ProjectileModule_ProvidePowerBlastFactory(module, appProvider, templateProvider);
  }

  /** Proxies {@link ProjectileModule#providePowerBlast(App, PowerBlastTemplate)}. */
  public static PowerBlast proxyProvidePowerBlast(
      ProjectileModule instance, App app, PowerBlastTemplate template) {
    return instance.providePowerBlast(app, template);
  }
}
