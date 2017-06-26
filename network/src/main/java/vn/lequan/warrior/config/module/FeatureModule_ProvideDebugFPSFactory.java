package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.features.debug.DebugFPS;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class FeatureModule_ProvideDebugFPSFactory implements Factory<DebugFPS> {
  private final FeatureModule module;

  private final Provider<App> appProvider;

  public FeatureModule_ProvideDebugFPSFactory(FeatureModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public DebugFPS get() {
    return Preconditions.checkNotNull(
        module.provideDebugFPS(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<DebugFPS> create(FeatureModule module, Provider<App> appProvider) {
    return new FeatureModule_ProvideDebugFPSFactory(module, appProvider);
  }

  /** Proxies {@link FeatureModule#provideDebugFPS(App)}. */
  public static DebugFPS proxyProvideDebugFPS(FeatureModule instance, App app) {
    return instance.provideDebugFPS(app);
  }
}
