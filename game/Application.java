package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(),new Door(),new RocketPad()
				, new Water(), new OxygenDispenser());

		GameMap gameMap;
		GameMap moonbaseMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...O....#....#....",
				"....#####....##O###....",
				".......................",
				".......................",
				".......................",
				"~~~~~~~~.............D.",
				"~~~~~~~~~~...........+.",
				"~~~~~~~~~~~............");


		List<String> moonbase_map = Arrays.asList(
				"..........",
				"..........",
				"..........",
				"###....###",
				"..###O##..",
				"..........",
				"..........",
				"..........",
				"........+.",
				"..........");


		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);

		moonbaseMap = new GameMap(groundFactory, moonbase_map);
		world.addMap(moonbaseMap);

		GoToMoonBase.addingNewAction(new MoveActorAction(moonbaseMap.at(8,8), "to secret Moonbase"));
		GoToEarth.addingNewAction(new MoveActorAction(gameMap.at(21,9), "to Earth"));

		Actor player = new PlayerStunable("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 5,10);
		player.addSkill(MapPlayerAt.EARTH);

		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0,0);
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  22,0);

		Miniboss boss = new Miniboss("Doctor Maybe", player);
		gameMap.addActor(boss,6,3);

		Goon goon = new Goon("Hobo", player);
		gameMap.addActor(goon,0,7);

		Q q = new Q("Q",player);
		gameMap.addActor(q, 12,10);

		Ninja ninja = new Ninja("Mochi", player);
		gameMap.addActor(ninja,9,2);

		RocketPlans rocketPlans = new RocketPlans();
		gameMap.addItem(rocketPlans,14,2);

		Spacesuit spacesuit = new Spacesuit();
		gameMap.addItem(spacesuit,21,7);

		Grunt gruntMoonBase = new Grunt("Mutated Armstrong",player);
		Goon goonMoonBase = new Goon("Kal El",player);
		Ninja ninjaMoonBase = new Ninja("ET",player);

		moonbaseMap.addActor(gruntMoonBase,3,7);
		moonbaseMap.addActor(goonMoonBase,6,7);
		moonbaseMap.addActor(ninjaMoonBase,2,5);

		YugoMaxx yugoMaxx = new YugoMaxx("Yugo Maxx", player);
		moonbaseMap.addActor(yugoMaxx, 0,0);

		moonbaseMap.addItem(new WaterPistol(), 0, 6);

		world.run();
	}

}

