package com.app.crud.events;

public class UserDeletionEvent <User> extends EntityEvent<User>  {

    private User user;

    public UserDeletionEvent(User user)
    {
        super(user);
        this.user=user;
    }
    public User getUser() {
        return user;
    }
}
