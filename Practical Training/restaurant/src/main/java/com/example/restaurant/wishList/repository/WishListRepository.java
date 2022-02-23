package com.example.restaurant.wishList.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishList.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
    // MemoryDbRepositoryAbstract에 <WishListEntity> 넣었기 때문에
    // MemoryDbRepositoryAbstract 에 <T> = <WishListEntity>
}
