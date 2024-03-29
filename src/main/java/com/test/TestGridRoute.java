package com.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItemBase;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "")
public class TestGridRoute extends VerticalLayout {

    public static class Item {
        String column1;
        String column2;
        String column3;
        String column4;
        String column5;

        public String getColumn1() {
            return column1;
        }

        public void setColumn1(String column1) {
            this.column1 = column1;
        }

        public String getColumn2() {
            return column2;
        }

        public void setColumn2(String column2) {
            this.column2 = column2;
        }

        public String getColumn3() {
            return column3;
        }

        public void setColumn3(String column3) {
            this.column3 = column3;
        }

        public String getColumn4() {
            return column4;
        }

        public void setColumn4(String column4) {
            this.column4 = column4;
        }

        public String getColumn5() {
            return column5;
        }

        public void setColumn5(String column5) {
            this.column5 = column5;
        }

        public Item(String column1, String column2, String column3, String column4, String column5) {
            this.column1 = column1;
            this.column2 = column2;
            this.column3 = column3;
            this.column4 = column4;
            this.column5 = column5;
        }
    }

    public TestGridRoute() {
       List<Item> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Item item = new Item("AAA"+i, "BBB"+i, "CCC"+i, "DDD"+i, "EEE"+i);
            data.add(item);
        }
        ListDataProvider dataProvider = new ListDataProvider(data);
        Grid grid = new Grid(Item.class);
        grid.setHeight("600px");
        grid.setWidth("1000px");
        grid.setDataProvider(dataProvider);
        grid.setColumnReorderingAllowed(true);
        grid.setMultiSort(true);
        HeaderRow secondHeaderRow = grid.appendHeaderRow();
        //FooterRow footerRow = grid.prependFooterRow();



        grid.getColumnByKey("column1").setWidth("200px").setResizable(true);
        grid.getColumnByKey("column2").setWidth("200px").setResizable(true);
        grid.getColumnByKey("column3").setWidth("200px").setResizable(true);
        grid.getColumnByKey("column4").setWidth("200px").setResizable(true);
        grid.getColumnByKey("column5").setWidth("200px").setResizable(true);


        GridContextMenu gridContextMenu = new GridContextMenu(grid);
        MenuItemBase menuItem =gridContextMenu.addItem("context menu opened", event -> Notification.show("clicked"));
        gridContextMenu.addGridContextMenuOpenedListener(e -> {
            GridContextMenu.GridContextMenuOpenedEvent event = (GridContextMenu.GridContextMenuOpenedEvent)e;
            if (!event.isOpened()) return;;
            menuItem.setText("context menu opened from column : "+event.getColumnId());
        });

        setHeightFull();
        add(grid);

        grid.setItemDetailsRenderer(
                new ComponentRenderer(activity -> {
                    VerticalLayout layout = new VerticalLayout();
                    layout.setSizeFull();
                    layout.setPadding(false);
                    layout.setSpacing(false);
                    for (int i = 0; i < 5; i++) {
                        Item item = new Item("AAA"+i, "BBB"+i, "CCC"+i, "DDD"+i, "EEE"+i);
                        data.add(item);
                    }
                    ListDataProvider dataProvider2 = new ListDataProvider(data);
                    Grid grid2 = new Grid(Item.class);
                    grid2.setWidthFull();
                    grid2.setDataProvider(dataProvider2);
                    grid2.getColumnByKey("column1").setWidth("200px").setResizable(true);
                    grid2.getColumnByKey("column2").setWidth("200px").setResizable(true);
                    grid2.getColumnByKey("column3").setWidth("200px").setResizable(true);
                    layout.add(grid2);
                    return layout;
                }));


        Button freeze = new Button("freeze 2 first columns", event -> {
            grid.getColumnByKey("column1").setFrozen(true);
            grid.getColumnByKey("column2").setFrozen(true);
        });
        Button unfreeze = new Button("unfreeze 2 first columns", event -> {
            grid.getColumnByKey("column1").setFrozen(false);
            grid.getColumnByKey("column2").setFrozen(false);
        });
        Button showPanel = new Button("show panel detail with embedded grid", event -> {
            Item item  = data.get(5);
            grid.setDetailsVisible(item, true);
        });
        Button hidePanel = new Button("close panel detail with embedded grid", event -> {
            Item item  = data.get(5);
            grid.setDetailsVisible(item, false);
        });
        Button detachGrid = new Button("detach grid", event -> {
            remove(grid);
        });
        Button attachGrid = new Button("attach grid", event -> {
            addComponentAtIndex(0, grid);
        });
        Button fullResync = new Button("full resync", event -> {
           Notification.show("resync", 5000, Notification.Position.BOTTOM_END);
        });

        add(freeze);
        add(unfreeze);
        add(showPanel);
        add(hidePanel);
        add(detachGrid);
        add(attachGrid);
        add(fullResync);

    }

}
