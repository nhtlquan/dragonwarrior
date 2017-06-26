package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.map.cells.Lamp;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CellModule_ProvideLampFactory implements Factory<Lamp> {
  private final CellModule module;

  private final Provider<App> appProvider;

  public CellModule_ProvideLampFactory(CellModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public Lamp get() {
    return Preconditions.checkNotNull(
        module.provideLamp(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Lamp> create(CellModule module, Provider<App> appProvider) {
    return new CellModule_ProvideLampFactory(module, appProvider);
  }

  /** Proxies {@link CellModule#provideLamp(App)}. */
  public static Lamp proxyProvideLamp(CellModule instance, App app) {
    return instance.provideLamp(app);
  }
}
