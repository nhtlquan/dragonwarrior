package vn.lequan.warrior.config.component;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.config.module.AppModule_ProvideAppFactory;
import vn.lequan.warrior.config.module.CellModule;
import vn.lequan.warrior.config.module.CellModule_ProvideCarpetFactory;
import vn.lequan.warrior.config.module.CellModule_ProvideLampFactory;
import vn.lequan.warrior.config.module.CellModule_ProvidePaintingFactory;
import vn.lequan.warrior.config.module.CellModule_ProvideSlabFactory;
import vn.lequan.warrior.config.module.CellModule_ProvideUnfilledFactory;
import vn.lequan.warrior.config.module.CellModule_ProvideWallFactory;
import vn.lequan.warrior.map.cells.Carpet;
import vn.lequan.warrior.map.cells.Lamp;
import vn.lequan.warrior.map.cells.Painting;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.map.cells.Unfilled;
import vn.lequan.warrior.map.cells.Wall;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerCellComponent implements CellComponent {
  private Provider<App> provideAppProvider;

  private Provider<Wall> provideWallProvider;

  private Provider<Slab> provideSlabProvider;

  private Provider<Carpet> provideCarpetProvider;

  private Provider<Unfilled> provideUnfilledProvider;

  private Provider<Lamp> provideLampProvider;

  private Provider<Painting> providePaintingProvider;

  private DaggerCellComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static CellComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideAppProvider =
        DoubleCheck.provider(AppModule_ProvideAppFactory.create(builder.appModule));

    this.provideWallProvider =
        CellModule_ProvideWallFactory.create(builder.cellModule, provideAppProvider);

    this.provideSlabProvider =
        CellModule_ProvideSlabFactory.create(builder.cellModule, provideAppProvider);

    this.provideCarpetProvider =
        CellModule_ProvideCarpetFactory.create(builder.cellModule, provideAppProvider);

    this.provideUnfilledProvider =
        CellModule_ProvideUnfilledFactory.create(builder.cellModule, provideAppProvider);

    this.provideLampProvider =
        CellModule_ProvideLampFactory.create(builder.cellModule, provideAppProvider);

    this.providePaintingProvider =
        CellModule_ProvidePaintingFactory.create(builder.cellModule, provideAppProvider);
  }

  @Override
  public Wall provideWall() {
    return provideWallProvider.get();
  }

  @Override
  public Slab provideSlab() {
    return provideSlabProvider.get();
  }

  @Override
  public Carpet provideCarpet() {
    return provideCarpetProvider.get();
  }

  @Override
  public Unfilled provideUnfilled() {
    return provideUnfilledProvider.get();
  }

  @Override
  public Lamp provideLamp() {
    return provideLampProvider.get();
  }

  @Override
  public Painting providePainting() {
    return providePaintingProvider.get();
  }

  public static final class Builder {
    private AppModule appModule;

    private CellModule cellModule;

    private Builder() {}

    public CellComponent build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      if (cellModule == null) {
        this.cellModule = new CellModule();
      }
      return new DaggerCellComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder cellModule(CellModule cellModule) {
      this.cellModule = Preconditions.checkNotNull(cellModule);
      return this;
    }
  }
}
