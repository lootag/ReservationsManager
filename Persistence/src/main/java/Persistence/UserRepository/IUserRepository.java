package Persistence.UserRepository;

import Persistence.Entities.User;

public interface IUserRepository{
    User SaveUser(User user);
}