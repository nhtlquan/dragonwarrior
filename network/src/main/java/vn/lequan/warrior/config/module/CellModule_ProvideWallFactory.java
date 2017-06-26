package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.map.cells.Wall;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CellModule_ProvideWallFactory implements Factory<Wall> {
  private final CellModule module;

  private final Provider<App> appProvider;

  public CellModule_ProvideWallFactory(CellModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public Wall get() {
    return Preconditions.checkNotNull(
        module.provideWall(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Wall> create(CellModule module, Provider<App> appProvider) {
    return new CellModule_ProvideWallFactory(module, appProvider);
  }

  /** Proxies {@link CellModule#provideWall(App)}. */
  public static Wall proxyProvideWall(CellModule instance, App app) {
    return instance.provideWall(app);
  }
}
