package UIHelper.BottomSheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import vn.namtran.basichelper.R;

import static UIHelper.BottomSheet.MenuSheetView.MenuType.GRID;
import static UIHelper.BottomSheet.MenuSheetView.MenuType.LIST;

/**
 * A SheetView that can represent a menu resource as a list or grid.
 * <p/>
 * A list can support submenus, and will include a divider and header for them where appropriate.
 * Grids currently don't support submenus, and don't in the Material Design spec either.
 */
@SuppressLint("ViewConstructor")
public class MenuSheetView extends FrameLayout {

    /**
     * A listener for menu item clicks in the sheet
     */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem item);
    }

    /**
     * The supported display types for the menu items.
     */
    public enum MenuType {LIST, GRID}

    private Menu menu;
    private final MenuType menuType;
    private ArrayList<SheetMenuItem> items = new ArrayList<>();
    private Adapter adapter;
    private AbsListView absListView;
    private final TextView titleView;

    /**
     * @param context Context to construct the view with
     * @param menuType LIST or GRID
     * @param titleRes String resource ID for the title
     * @param listener Listener for menu item clicks in the sheet
     */
    public MenuSheetView(final Context context, final MenuType menuType, @StringRes final int titleRes, final OnMenuItemClickListener listener) {
        this(context, menuType, context.getString(titleRes), listener);
    }

    /**
     * @param context Context to construct the view with
     * @param menuType LIST or GRID
     * @param title Title for the sheet. Can be null
     * @param listener Listener for menu item clicks in the sheet
     */
    public MenuSheetView(final Context context, final MenuType menuType, @Nullable final CharSequence title, final OnMenuItemClickListener listener) {
        super(context);

        // Set up the menu
        this.menu = new PopupMenu(context, null).getMenu();  // Dirty hack to get a menu instance since MenuBuilder isn't public ಠ_ಠ
        this.menuType = menuType;

        // Inflate the appropriate view and set up the absListView
        inflate(context, menuType == GRID ? R.layout.grid_sheet_view : R.layout.list_sheet_view, this);
        absListView = (AbsListView) findViewById(menuType == GRID ? R.id.grid : R.id.list);
        if (listener != null) {
            absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listener.onMenuItemClick(adapter.getItem(position).getMenuItem());
                }
            });
        }

        // Set up the title
        titleView = (TextView) findViewById(R.id.title);
        setTitle(title);
    }

    /**
     * Inflates a menu resource into the menu backing this sheet.
     *
     * @param menuRes Menu resource ID
     */
    public void inflateMenu(@MenuRes int menuRes) {
        if (menuRes != -1) {
            MenuInflater inflater = new MenuInflater(getContext());
            inflater.inflate(menuRes, menu);
        }

        prepareMenuItems();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.adapter = new Adapter();
        absListView.setAdapter(this.adapter);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (menuType == GRID) {
            float density = getResources().getDisplayMetrics().density;
            ((GridView) absListView).setNumColumns((int) (getWidth() / (100 * density)));
        }
    }

    /**
     * Flattens the visible menu items of {@link #menu} into {@link #items},
     * while inserting separators between items when necessary.
     *
     * Adapted from the Design support library's NavigationMenuPresenter implementation
     */
    private void prepareMenuItems() {
        items.clear();
        int currentGroupId = 0;

        // Iterate over the menu items
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.isVisible()) {
                if (item.hasSubMenu()) {
                    // Flatten the submenu
                    SubMenu subMenu = item.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (menuType == LIST) {
                            items.add(SheetMenuItem.SEPARATOR);

                            // Add a header item if it has text
                            if (!TextUtils.isEmpty(item.getTitle())) {
                                items.add(SheetMenuItem.of(item));
                            }
                        }

                        // Add the sub-items
                        for (int subI = 0, size = subMenu.size(); subI < size; subI++) {
                            MenuItem subMenuItem = subMenu.getItem(subI);
                            if (subMenuItem.isVisible()) {
                                items.add(SheetMenuItem.of(subMenuItem));
                            }
                        }

                        // Add one more separator to the end to close it off if we have more items
                        if (menuType == LIST && i != menu.size() - 1) {
                            items.add(SheetMenuItem.SEPARATOR);
                        }
                    }
                } else {
                    int groupId = item.getGroupId();
                    if (groupId != currentGroupId && menuType == LIST) {
                        items.add(SheetMenuItem.SEPARATOR);
                    }
                    items.add(SheetMenuItem.of(item));
                    currentGroupId = groupId;
                }
            }
        }
    }

    /**
     * @return The current {@link Menu} instance backing this sheet. Note that this is mutable, and
     *         you should call {@link #updateMenu()} after any changes.
     */
    public Menu getMenu() {
        return this.menu;
    }

    /**
     * Invalidates the current internal representation of the menu and rebuilds it. Should be used
     * if the developer dynamically adds items to the Menu returned by {@link #getMenu()}
     */
    public void updateMenu() {
        // Invalidate menu and rebuild it, useful if the user has dynamically added menu items.
        prepareMenuItems();
    }

    /**
     * @return The {@link MenuType} this instance was built with
     */
    public MenuType getMenuType() {
        return this.menuType;
    }

    /**
     * Sets the title text of the sheet
     *
     * @param resId String resource ID for the text
     */
    public void setTitle(@StringRes int resId) {
        setTitle(getResources().getText(resId));
    }

    /**
     * Sets the title text of the sheet
     *
     * @param title Title text to use
     */
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            titleView.setText(title);
        } else {
            titleView.setVisibility(GONE);

            // Add some padding to the top to account for the missing title
            absListView.setPadding(absListView.getPaddingLeft(), absListView.getPaddingTop() + MenuSheetView.dp2px(getContext(), 8f), absListView.getPaddingRight(), absListView.getPaddingBottom());
        }
    }

    /**
     * @return The current title text of the sheet
     */
    public CharSequence getTitle() {
        return titleView.getText();
    }

    private class Adapter extends BaseAdapter {

        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private static final int VIEW_TYPE_SEPARATOR = 2;

        private final LayoutInflater inflater;

        public Adapter() {
            this.inflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public SheetMenuItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            SheetMenuItem item = getItem(position);
            if (item.isSeparator()) {
                return VIEW_TYPE_SEPARATOR;
            } else if (item.getMenuItem().hasSubMenu()) {
                return VIEW_TYPE_SUBHEADER;
            } else {
                return VIEW_TYPE_NORMAL;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SheetMenuItem item = getItem(position);
            int viewType = getItemViewType(position);

            switch (viewType) {
                case VIEW_TYPE_NORMAL:
                    NormalViewHolder holder;
                    if (convertView == null) {
                        convertView = inflater.inflate(menuType == GRID ? R.layout.sheet_grid_item : R.layout.sheet_list_item, parent, false);
                        holder = new NormalViewHolder(convertView);
                        convertView.setTag(holder);
                    } else {
                        holder = (NormalViewHolder) convertView.getTag();
                    }
                    holder.bindView(item);
                    break;
                case VIEW_TYPE_SUBHEADER:
                    if (convertView == null) {
                        convertView = inflater.inflate(R.layout.sheet_list_item_subheader, parent, false);
                    }
                    TextView subHeader = (TextView) convertView;
                    subHeader.setText(item.getMenuItem().getTitle());
                    break;
                case VIEW_TYPE_SEPARATOR:
                    if (convertView == null) {
                        convertView = inflater.inflate(R.layout.sheet_list_item_separator, parent, false);
                    }
                    break;
            }

            return convertView;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return getItem(position).isEnabled();
        }

        class NormalViewHolder {
            final ImageView icon;
            final TextView label;

            NormalViewHolder(View root) {
                icon = (ImageView) root.findViewById(R.id.icon);
                label = (TextView) root.findViewById(R.id.label);
            }

            public void bindView(SheetMenuItem item) {
                icon.setImageDrawable(item.getMenuItem().getIcon());
                label.setText(item.getMenuItem().getTitle());
            }
        }
    }

    private static class SheetMenuItem {

        private static final SheetMenuItem SEPARATOR = new SheetMenuItem(null);

        private final MenuItem menuItem;

        private SheetMenuItem(MenuItem item) {
            menuItem = item;
        }

        public static SheetMenuItem of(MenuItem item) {
            return new SheetMenuItem(item);
        }

        public boolean isSeparator() {
            return this == SEPARATOR;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public boolean isEnabled() {
            // Separators and subheaders never respond to click
            return menuItem != null && !menuItem.hasSubMenu() && menuItem.isEnabled();
        }

    }

    /**
     * Convert a dp float value to pixels
     *
     * @param dp      float value in dps to convert
     * @return DP value converted to pixels
     */
    static int dp2px(Context context, float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return Math.round(px);
    }
}
