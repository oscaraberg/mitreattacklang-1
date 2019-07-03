package attack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertTrue;

import core.Asset;
import core.AttackStep;
import core.AttackStepMax;
import core.AttackStepMin;
import core.Defense;
public class OS extends Asset {
   public User user;

   public PasswordPolicyDiscovery passwordPolicyDiscovery;
   public BruteForce bruteForce;

   public OS() {
      super();
      AttackStep.allAttackSteps.remove(passwordPolicyDiscovery);
      passwordPolicyDiscovery = new PasswordPolicyDiscovery(this.name);
      AttackStep.allAttackSteps.remove(bruteForce);
      bruteForce = new BruteForce(this.name);
      assetClassName = "OS";
   }

   public OS(String name) {
      super(name);
      AttackStep.allAttackSteps.remove(passwordPolicyDiscovery);
      passwordPolicyDiscovery = new PasswordPolicyDiscovery(this.name);
      AttackStep.allAttackSteps.remove(bruteForce);
      bruteForce = new BruteForce(this.name);
      assetClassName = "OS";
   }


   public class PasswordPolicyDiscovery extends AttackStepMin {
   public PasswordPolicyDiscovery(String name) {
      super(name);
      assetClassName = "OS";
   }
@Override
public void setExpectedParents() {
if (user != null) {
addExpectedParent(user.userRights);
}
}
@Override
public void updateChildren(java.util.Set<AttackStep> activeAttackSteps) {
bruteForce.updateTtc(this, ttc, activeAttackSteps);
}
      @Override
      public double localTtc() {
         return ttcHashMap.get("OS.passwordPolicyDiscovery");
      }

   }

   public class BruteForce extends AttackStepMin {
   public BruteForce(String name) {
      super(name);
      assetClassName = "OS";
   }
@Override
public void setExpectedParents() {
addExpectedParent(passwordPolicyDiscovery);
}
      @Override
      public double localTtc() {
         return ttcHashMap.get("OS.bruteForce");
      }

   }

      public void addUser(User user) {
         this.user = user;
         user.os.add(this);
      }

   @Override
   public String getAssociatedAssetClassName(String roleName) {
      if (roleName.equals("user")) {
         return user.getClass().getName();
      }
      return null;
   }
   @Override
   public java.util.Set<Asset> getAssociatedAssets(String roleName) {
      java.util.Set<Asset> assets = new java.util.HashSet<>();
      if (roleName.equals("user")  && user != null) {
         assets.add(user);
         return assets;
      }
      assertTrue("The asset " + this.toString() + " does not feature the role name " + roleName + ".", false);
      return null;
   }
   @Override
   public java.util.Set<Asset> getAllAssociatedAssets() {
      java.util.Set<Asset> assets = new java.util.HashSet<>();
      if (user != null) {
         assets.add(user);
      }
      return assets;
   }
}
