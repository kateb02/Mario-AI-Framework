<p align="center">
  <img width="400" height="400" src="https://raw.githubusercontent.com/amidos2006/Mario-AI-Framework/master/img/frameworkAD.gif">
</p>

This is an updated version for the Mario-AI framework. This new code will support better interface for planning track, level generation track, and possibly the learning track in the future. The framework comes with multiple different planning agents, level generators and thousands of levels that varies between generated levels and original mario level. Also, the framework is compatible with [Video Game Level Corpus (VGLC)](https://github.com/TheVGLC/TheVGLC) processed notations.

### Features
------
- Better Interface for the framework
- Faster framework
- Using the original mario art
- Eleven different playing agents
- Agents now have a forward model, no more hacks for that
- Observation grids can be centered around mario or can reflect the current screen.
- Helper classes to check the observation grid instead of comparing integers
- Five different level generators
- Level generator have a forward model to test the levels
- Thousands of generated levels from winners of the level generation track
- Fifteen levels from the original mario bros
- Support event history for major game events
- A human readable level files

### How to use
------
#### Planning Track
Download the repo and run the [`PlayLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/PlayLevel.java) file. It will run [`robinBaumgarten`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents/robinBaumgarten) A* agent on the [first Mario level](https://github.com/amidos2006/Mario-AI-Framework/blob/master/levels/original/lvl-1.txt) from the original Super Mario Bros. The game will run for 20 time ticks and with Mario starting as small mario and visuals appearing. To change the agent just change the package name of the agent in the following code
```
printResults(game.runGame(new agents.robinBaumgarten.Agent(), getLevel("levels/original/lvl-1.txt"), 20, 0, true));
```
to any of the package names that are found in [`src/agents/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents) folder, feel free to use any in your work. If you want to play a level yourself uncomment the following code in [`PlayLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/PlayLevel.java) file
```
//printResults(game.playGame(getLevel("levels/original/lvl-1.txt"), 200, 0));
```
and comment the agent running line from before. This code will run the framework to play the [first mario level](https://github.com/amidos2006/Mario-AI-Framework/blob/master/levels/original/lvl-1.txt) of the original Super Mario Bros with 200 tick on the game clock and with Mario starting as small mario. Feel free to change the `0` to `1` to start as Large Mario or `2` to start as Fire Mario.

#### Level Generation Track
Download the repo and run the [`GenerateLevel.java`](https://github.com/amidos2006/Mario-AI-Framework/blob/master/src/GenerateLevel.java) from the [`src/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src) folder to test the framework. It will run `notch` generator to generate a level then it will run [`robinBaumgarten`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/agents/robinBaumgarten) A* agent to play that generated level. Feel free to try another generators by changing the package name of generator in the following line
```
MarioLevelGenerator generator = new levelGenerators.notch.LevelGenerator();
```
to any of the other package names of the other generator that can be found in in [`src/levelGenerators/`](https://github.com/amidos2006/Mario-AI-Framework/tree/master/src/levelGenerators) folder, feel free to use any in your work. The generators runs for maximum time of 5 hours to generate a level of 150x16 tiles using the following line:
```
String level = generator.getGeneratedLevel(new MarioLevelModel(150, 16), new MarioTimer(5*60*60*1000));
```
If you want to play the level by yourself or change the AI playing agent check the Planning Track subsection.

### Related Papers
------
The following paper describes the Mario AI benchmark:
- [[2012] The Mario AI Benchmark and Competitions](http://julian.togelius.com/Karakovskiy2012The.pdf) by Sergey Karakovskiy and Julian Togelius. Published in the IEEE Transactions on Computational Intelligence and AI in Games (TCIAG), volume 4 issue 1, 55-67.

The following list show all the papers that talk about playing the game in Mario-AI framework:
- [[2009] Super Mario Evolution](http://julian.togelius.com/Togelius2009Super.pdf) by Julian Togelius, Sergey Karakovskiy, Jan Koutnik and Jurgen Schmidhuber. Published in the proceedings of the IEEE Symposium on Computational Intelligence and Games.
- [[2010] The 2009 Mario AI Competition](http://julian.togelius.com/Togelius2010The.pdf) by Julian Togelius, Sergey Karakovskiy and Robin Baumgarten. Published in the proceedings of the IEEE Congress on Evolutionary Computation (CEC).

The following list show all the papers that talk about level generation in Mario-AI framework:
- [[2010] The 2010 Mario AI Championship: Level Generation Track](https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=6003769) by Noor Shaker, Julian Togelius, Georgios N. Yannakakis, Ben Weber, Tomoyuki Shimizu, Tomonori Hashiyama, Nathan Sorenson, Philippe Pasquier, Peter Mawhorter, Glen Takahashi, Gillian Smith, and Robin Baumgarten. Published in the IEEE Transactions on Computational Intelligence and Games.
- [[2010] Towards Automatic Personalized Content Generation for Platform Games](https://www.aaai.org/ocs/index.php/AIIDE/AIIDE10/paper/viewFile/2135/2546) by Noor Shaker, Georgios Yannakakis and Julian Togelius. Published in the procedings of AIIDE Conference on AI and Interactive Digital Entertainment.
- [[2018] Generating Levels That Teach Mechanics](https://arxiv.org/pdf/1807.06734.pdf) by Michael Cerny Green, Ahmed Khalifa, Gabriella A. B. Barros, Andy Nealen and Julian Togelius. Published in the proceeding of Foundation of Digital Games.
- [[2018] Evolving Mario Levels in the Latent Space of a Deep Convolutional Generative Adversarial Network](https://arxiv.org/pdf/1805.00728.pdf) by Vanessa Volz, Jacob Schrum, Jialin Liu, Simon M. Lucas, Adam Smith and Sebastian Risi. Published in the proceeding of GECCO.
- [[2019] Intentional Computational Level Design](https://arxiv.org/pdf/1904.08972.pdf) by Ahmed Khalifa, Michael Cerny Green, Gabriella A. B. Barros and Julian Togelius. Published in the proceeding of GECCO.

The following list show all any papers that doesn't fit in the previous categories but still uses Mario-AI framework:
- [[2009] Modeling Player Experience in Super Mario Bros](http://julian.togelius.com/Pedersen2009Modeling.pdf) by Chris Pedersen, Julian Togelius and Georgios N. Yannakakis. Published in the Proceedings of the IEEE Symposium on Computational Intelligence and Games.

We are very sorry, if we forgot any Mario-AI research paper. In that case, please [contact us](mailto:ahmed@akhalifa.com) to add it to the list.

### Missing Features
------
- ~~The MarioAI framework core engine~~
- ~~Implementing a forward model and multiple different observations (based around mario/based around the screen center)~~
- ~~Implementing the original SMB graphics instead of Mario world graphics~~
- ~~Adding multiple agents from the previous competition~~
- ~~Isolating particle effects from game sprites~~
- ~~Only using the first SMB action set (no more shell carrying/wall jumping)~~
- ~~Documenting the interface~~
- ~~Adding Generated Levels~~
- ~~Adding the level generator interface~~
- ~~Adding the level generators to the framework~~
- ~~Better way to check the observation grid Using TileType and SpriteType~~
- ~~Adding event history for the game and agent to MarioResults~~
- ~~Modifying the original Mario levels to include more details~~
- Mix the TileType and TileFeature class
- Add more stats to MarioResult class similar to Gameplay Metrics
- Adding a simple MCTS agent and simple A* agent
- Koopa shells can come back to life after stomping on it
- Adding Monte Mario agent
- Multiple different backgrounds/palettes that the user can select from.
- Documenting the whole engine
- Mimicking the original SMB physics instead of SMW physics
- Adding the learning track interface

### Copyrights
------
This framework is not endorsed by Nintendo and only intended for research purposes. Mario is a Nintendo character which the authors doesn't own any rights to it. This framework by [Ahmed Khalifa](https://scholar.google.com/citations?user=DRcyg5kAAAAJ&hl=en), based on the original Mario AI Framework by [Sergey Karakovskiy](https://scholar.google.se/citations?user=6cEAqn8AAAAJ&hl=en), [Noor Shaker](https://scholar.google.com/citations?user=OK9tw1AAAAAJ&hl=en), and [Julian Togelius](https://scholar.google.com/citations?user=lr4I9BwAAAAJ&hl=en), which in turn was based on [Infinite Mario Bros](https://fantendo.fandom.com/wiki/Infinite_Mario_Bros.) by Markus Persson.