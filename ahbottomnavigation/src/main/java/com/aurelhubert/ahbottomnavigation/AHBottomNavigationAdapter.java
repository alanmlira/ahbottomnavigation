package com.aurelhubert.ahbottomnavigation;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.MenuRes;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AHBottomNavigationAdapter {

	private Menu mMenu, mSelectedMenu;
	private List<AHBottomNavigationItem> navigationItems;

	/**
	 * Constructor
	 *
	 * @param activity
	 * @param menuRes
	 */
	public AHBottomNavigationAdapter(Activity activity, @MenuRes int menuRes) {
		PopupMenu popupMenu = new PopupMenu(activity, null);
		mMenu = popupMenu.getMenu();
		activity.getMenuInflater().inflate(menuRes, mMenu);
	}

	/**
	 * Constructor
	 *
	 * @param activity
	 * @param menuRes
	 * @param selectedMenu
	 */
	public AHBottomNavigationAdapter(Activity activity, @MenuRes int menuRes, @MenuRes int selectedMenu) {
		PopupMenu popupMenu = new PopupMenu(activity, null);
		mMenu = popupMenu.getMenu();
		mSelectedMenu = popupMenu.getMenu();
		activity.getMenuInflater().inflate(menuRes, mMenu);
		activity.getMenuInflater().inflate(menuRes, mSelectedMenu);
	}

	/**
	 * Setup bottom navigation
	 *
	 * @param ahBottomNavigation AHBottomNavigation: Bottom navigation
	 */
	public void setupWithBottomNavigation(AHBottomNavigation ahBottomNavigation) {
		setupWithBottomNavigation(ahBottomNavigation, null);
	}

	/**
	 * Setup bottom navigation (with colors)
	 *
	 * @param ahBottomNavigation AHBottomNavigation: Bottom navigation
	 * @param colors             int[]: Colors of the item
	 */
	public void setupWithBottomNavigation(AHBottomNavigation ahBottomNavigation, @ColorInt int[] colors) {
		if (navigationItems == null) {
			navigationItems = new ArrayList<>();
		} else {
			navigationItems.clear();
		}

		if (mMenu != null) {
			int menuSize = mMenu.size();
			if (mSelectedMenu != null) {
				if (mMenu.size() > mSelectedMenu.size()) {
					menuSize = mSelectedMenu.size();
				}
			}

			for (int i = 0; i < menuSize; i++) {
				MenuItem item = mMenu.getItem(i);
				if (colors != null && colors.length >= menuSize && colors[i] != 0) {
					AHBottomNavigationItem navigationItem = new AHBottomNavigationItem(String.valueOf(item.getTitle()), item.getIcon(), colors[i]);
					if (mSelectedMenu != null) {
						MenuItem selectedItem = mSelectedMenu.getItem(i);
						navigationItem = new AHBottomNavigationItem(String.valueOf(item.getTitle()), item.getIcon(), selectedItem.getIcon(), colors[i]);
					}
					navigationItems.add(navigationItem);
				} else {
					AHBottomNavigationItem navigationItem = new AHBottomNavigationItem(String.valueOf(item.getTitle()), item.getIcon());
					if (mSelectedMenu != null) {
						MenuItem selectedItem = mSelectedMenu.getItem(i);
						navigationItem = new AHBottomNavigationItem(String.valueOf(item.getTitle()), item.getIcon(), selectedItem.getIcon());
					}
					navigationItems.add(navigationItem);
				}
			}
			ahBottomNavigation.removeAllItems();
			ahBottomNavigation.addItems(navigationItems);
		}
	}

	/**
	 * Get Menu Item
	 *
	 * @param index
	 * @return
	 */
	public MenuItem getMenuItem(int index) {
		return mMenu.getItem(index);
	}

	/**
	 * Get Navigation Item
	 *
	 * @param index
	 * @return
	 */
	public AHBottomNavigationItem getNavigationItem(int index) {
		return navigationItems.get(index);
	}

	/**
	 * Get position by menu id
	 *
	 * @param menuId
	 * @return
	 */
	public Integer getPositionByMenuId(int menuId) {
		for (int i = 0; i < mMenu.size(); i++) {
			if (mMenu.getItem(i).getItemId() == menuId)
				return i;
		}
		return null;
	}
}