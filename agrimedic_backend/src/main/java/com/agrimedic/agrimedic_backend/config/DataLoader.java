package com.agrimedic.agrimedic_backend.config;



import com.agrimedic.agrimedic_backend.entity.*;
import com.agrimedic.agrimedic_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final CentralizedProductRepository fertRepo;
    private final IssueRepository issueRepo;
    private final FertilizerOrderRepository orderRepo;

    public DataLoader(UserRepository userRepo, CentralizedProductRepository fertRepo, IssueRepository issueRepo, FertilizerOrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.fertRepo = fertRepo;
        this.issueRepo = issueRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only load if empty
        if (userRepo.count() > 0) return;

        User farmer1 = new User("Ramesh Farmer", "ramesh@example.com", "pass123", "9999999991", Role.ROLE_FARMER);
        User farmer2 = new User("Sita Farmer", "sita@example.com", "pass123", "9999999992", Role.ROLE_FARMER);
        User expert = new User("Dr. Patel", "patel@example.com", "expert123", "9999999993", Role.ROLE_EXPERT);
        User shopOwner = new User("Mahesh Shop", "mahesh@example.com", "shop123", "9999999994", Role.ROLE_SHOPOWNER);
        User admin = new User("Root Admin", "admin@example.com", "admin123", "9999999995", Role.ROLE_ADMIN);

        // Use userRepo to save (UserService would hash, but for quick demo we'll save and let service handle later)
        // For proper hashed passwords, we call the service; but to keep dependency simple, we'll encode here
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder enc = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        farmer1.setPassword(enc.encode(farmer1.getPassword()));
        farmer2.setPassword(enc.encode(farmer2.getPassword()));
        expert.setPassword(enc.encode(expert.getPassword()));
        shopOwner.setPassword(enc.encode(shopOwner.getPassword()));
        admin.setPassword(enc.encode(admin.getPassword()));

        userRepo.save(farmer1);
        userRepo.save(farmer2);
        userRepo.save(expert);
        userRepo.save(shopOwner);
        userRepo.save(admin);

        // Fertilizers
CentralizedProduct f1 = new CentralizedProduct();
        f1.setName("Urea");
        f1.setBrand("NPK");
        f1.setPrice(300.0);
        f1.setStock(50);
        f1.setShopOwner(shopOwner);

        CentralizedProduct f2 = new CentralizedProduct();
        f2.setName("DAP");
        f2.setBrand("GrowWell");
        f2.setPrice(450.0);
        f2.setStock(40);
        f2.setShopOwner(shopOwner);

        CentralizedProduct f3 = new CentralizedProduct();
        f3.setName("Potash");
        f3.setBrand("K-Plus");
        f3.setPrice(400.0);
        f3.setStock(30);
        f3.setShopOwner(shopOwner);

        CentralizedProduct f4 = new CentralizedProduct();
        f4.setName("Organic Mix");
        f4.setBrand("GreenFarm");
        f4.setPrice(250.0);
        f4.setStock(60);
        f4.setShopOwner(shopOwner);

        CentralizedProduct f5 = new CentralizedProduct();
        f5.setName("Micronutrient");
        f5.setBrand("MicroBoost");
        f5.setPrice(500.0);
        f5.setStock(20);
        f5.setShopOwner(shopOwner);

        fertRepo.save(f1);
        fertRepo.save(f2);
        fertRepo.save(f3);
        fertRepo.save(f4);
        fertRepo.save(f5);

        // Issues
        Issue i1 = new Issue();
        i1.setTitle("Leaf spots");
        i1.setDescription("Brown spots on leaves");
        i1.setImageUrl("/images/leaf1.jpg");
        i1.setFarmer(farmer1);

        Issue i2 = new Issue();
        i2.setTitle("Yellowing");
        i2.setDescription("Yellow leaves");
        i2.setImageUrl("/images/leaf2.jpg");
        i2.setFarmer(farmer2);

        Issue i3 = new Issue();
        i3.setTitle("Stem rot");
        i3.setDescription("Stem is soft");
        i3.setImageUrl("/images/stem1.jpg");
        i3.setFarmer(farmer1);

        Issue i4 = new Issue();
        i4.setTitle("Pest holes");
        i4.setDescription("Insects eating leaves");
        i4.setImageUrl("/images/pest1.jpg");
        i4.setFarmer(farmer2);

        Issue i5 = new Issue();
        i5.setTitle("Wilting");
        i5.setDescription("Plant wilting in morning");
        i5.setImageUrl("/images/wilt1.jpg");
        i5.setFarmer(farmer1);

        issueRepo.save(i1);
        issueRepo.save(i2);
        issueRepo.save(i3);
        issueRepo.save(i4);
        issueRepo.save(i5);

        // Orders
        FertilizerOrder o1 = new FertilizerOrder();
        o1.setFarmer(farmer1);
        o1.setFertilizer(f1);
        o1.setQuantity(5);

        FertilizerOrder o2 = new FertilizerOrder();
        o2.setFarmer(farmer2);
        o2.setFertilizer(f2);
        o2.setQuantity(10);

        FertilizerOrder o3 = new FertilizerOrder();
        o3.setFarmer(farmer1);
        o3.setFertilizer(f3);
        o3.setQuantity(2);

        FertilizerOrder o4 = new FertilizerOrder();
        o4.setFarmer(farmer2);
        o4.setFertilizer(f1);
        o4.setQuantity(3);

        FertilizerOrder o5 = new FertilizerOrder();
        o5.setFarmer(farmer1);
        o5.setFertilizer(f4);
        o5.setQuantity(1);

        // Decrement stocks for orders (simple)
        f1.setStock(f1.getStock() - 5 - 3); // two orders
        f2.setStock(f2.getStock() - 10);
        f3.setStock(f3.getStock() - 2);
        f4.setStock(f4.getStock() - 1);

        fertRepo.save(f1);
        fertRepo.save(f2);
        fertRepo.save(f3);
        fertRepo.save(f4);

        orderRepo.save(o1);
        orderRepo.save(o2);
        orderRepo.save(o3);
        orderRepo.save(o4);
        orderRepo.save(o5);
    }
}
