package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.LeaveChat;

public interface LeaveChatRepository extends JpaRepository<LeaveChat, Integer> {
    List<LeaveChat> findByLeaveIdOrderBySentAtAsc(int leaveId);
}
