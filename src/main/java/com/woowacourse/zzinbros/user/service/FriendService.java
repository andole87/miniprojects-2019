package com.woowacourse.zzinbros.user.service;

import com.woowacourse.zzinbros.user.domain.Friend;
import com.woowacourse.zzinbros.user.domain.FriendRequest;
import com.woowacourse.zzinbros.user.domain.User;
import com.woowacourse.zzinbros.user.domain.repository.FriendRepository;
import com.woowacourse.zzinbros.user.domain.repository.FriendRequestRepository;
import com.woowacourse.zzinbros.user.dto.FriendRequestDto;
import com.woowacourse.zzinbros.user.dto.UserResponseDto;
import com.woowacourse.zzinbros.user.exception.AlreadyFriendRequestExist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;
    private final UserService userService;

    public FriendService(FriendRequestRepository friendRequestRepository,
                         FriendRepository friendRepository,
                         UserService userService) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public boolean sendFriendRequest(final UserResponseDto requestUser, final FriendRequestDto friendRequested) {
        User sender = userService.findUserById(requestUser.getId());
        User receiver = userService.findUserById(friendRequested.getRequestFriendId());
        if (!friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            friendRequestRepository.save(new FriendRequest(sender, receiver));
            return true;
        }
        throw new AlreadyFriendRequestExist("Already Friend Request");
    }

    public Set<UserResponseDto> findFriendsByUser(final long id) {
        User owner = userService.findUserById(id);
        return this.friendToUserResponseDto(friendRepository.findAllByOwner(owner));
    }

    public Set<UserResponseDto> findFriendRequestsByUser(final UserResponseDto loginUserDto) {
        return findFriendRequestsByUserId(loginUserDto.getId());
    }

    public Set<UserResponseDto> findFriendRequestsByUserId(final long id) {
        User owner = userService.findUserById(id);
        return friendRequestToUserResponseDto(friendRequestRepository.findAllByReceiver(owner));

    }

    public Set<UserResponseDto> friendToUserResponseDto(Set<Friend> friends) {
        return friends.stream()
                .map(friend -> friend.getSlave())
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toSet());
    }

    public Set<UserResponseDto> friendRequestToUserResponseDto(Set<FriendRequest> friends) {
        return friends.stream()
                .map(friend -> friend.getSender())
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toSet());
    }

}
