package com.example.user_microservice.grpc;

import com.example.grpccommon.user.UserQueryServiceGrpc;
import com.example.grpccommon.user.UserRequest;
import com.example.grpccommon.user.UserResponse;
import com.example.user_microservice.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserQueryServiceImpl extends UserQueryServiceGrpc.UserQueryServiceImplBase {

    private final UserRepository userRepository;

    @Override
    public void getUserIdByUsername(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        String username = request.getUsername();
        Long userId = userRepository.findByEmail(username)
                .map(user -> user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserResponse response = UserResponse.newBuilder()
                .setUserId(userId)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

