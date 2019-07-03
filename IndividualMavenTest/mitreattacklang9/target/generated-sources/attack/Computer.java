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
public class Computer extends Asset {
   public OS os;

   public InfectedComputer infectedComputer;

   public Computer() {
      super();
      AttackStep.allAttackSteps.remove(infectedComputer);
      infectedComputer = new InfectedComputer(this.name);
      assetClassName = "Computer";
   }

   public Computer(String name) {
      super(name);
      AttackStep.allAttackSteps.remove(infectedComputer);
      infectedComputer = new InfectedComputer(this.name);
      assetClassName = "Computer";
   }


   public class InfectedComputer extends AttackStepMin {
   public InfectedComputer(String name) {
      super(name);
      assetClassName = "Computer";
   }
@Override
public void setExpectedParents() {
if (os != null) {
addExpectedParent(os.runCode);
}
}
      @Override
      public double localTtc() {
         return ttcHashMap.get("Computer.infectedComputer");
      }

   }

      public void addOs(OS os) {
         this.os = os;
         os.computer.add(this);
      }

   @Override
   public String getAssociatedAssetClassName(String roleName) {
      if (roleName.equals("os")) {
         return os.getClass().getName();
      }
      return null;
   }
   @Override
   public java.util.Set<Asset> getAssociatedAssets(String roleName) {
      java.util.Set<Asset> assets = new java.util.HashSet<>();
      if (roleName.equals("os")  && os != null) {
         assets.add(os);
         return assets;
      }
      assertTrue("The asset " + this.toString() + " does not feature the role name " + roleName + ".", false);
      return null;
   }
   @Override
   public java.util.Set<Asset> getAllAssociatedAssets() {
      java.util.Set<Asset> assets = new java.util.HashSet<>();
      if (os != null) {
         assets.add(os);
      }
      return assets;
   }
}
