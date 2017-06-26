package vn.lequan.warrior;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.features.debug.DebugBox2D;
import vn.lequan.warrior.features.debug.DebugFPS;
import vn.lequan.warrior.views.menu.MenuView;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class Main_MembersInjector implements MembersInjector<Main> {
  private final Provider<DebugFPS> debugFPSProvider;

  private final Provider<DebugBox2D> debugBox2DProvider;

  private final Provider<MenuView> menuViewProvider;

  public Main_MembersInjector(
      Provider<DebugFPS> debugFPSProvider,
      Provider<DebugBox2D> debugBox2DProvider,
      Provider<MenuView> menuViewProvider) {
    assert debugFPSProvider != null;
    this.debugFPSProvider = debugFPSProvider;
    assert debugBox2DProvider != null;
    this.debugBox2DProvider = debugBox2DProvider;
    assert menuViewProvider != null;
    this.menuViewProvider = menuViewProvider;
  }

  public static MembersInjector<Main> create(
      Provider<DebugFPS> debugFPSProvider,
      Provider<DebugBox2D> debugBox2DProvider,
      Provider<MenuView> menuViewProvider) {
    return new Main_MembersInjector(debugFPSProvider, debugBox2DProvider, menuViewProvider);
  }

  @Override
  public void injectMembers(Main instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.debugFPS = debugFPSProvider.get();
    instance.debugBox2D = debugBox2DProvider.get();
    instance.menuView = menuViewProvider.get();
  }

  public static void injectDebugFPS(Main instance, Provider<DebugFPS> debugFPSProvider) {
    instance.debugFPS = debugFPSProvider.get();
  }

  public static void injectDebugBox2D(Main instance, Provider<DebugBox2D> debugBox2DProvider) {
    instance.debugBox2D = debugBox2DProvider.get();
  }

  public static void injectMenuView(Main instance, Provider<MenuView> menuViewProvider) {
    instance.menuView = menuViewProvider.get();
  }
}
