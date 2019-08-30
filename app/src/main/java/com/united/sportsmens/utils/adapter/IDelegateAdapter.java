package com.united.sportsmens.utils.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IDelegateAdapter {

    Object getItem(int position);

    @NonNull
    List<Object> getItems();

    int getItemPosition(Object item);

    void setItems(@NonNull List<?> items);

    void addItem(@NonNull Object item);

    void clearItems();

    int indexOf(@Nullable Object item);

    void removeItem(@NotNull Object item);

    void removeItemByPosition(int pos);

    void addItem(int pos, Object item);

    void removeItems(List<?> items);

    void changeItem(@NotNull Object item);

    void changeItemByPosition(int pos, @NonNull Object item);

    void addItems(@NotNull List<?> items);

    void addItems(int pos, @NotNull List items);
}
